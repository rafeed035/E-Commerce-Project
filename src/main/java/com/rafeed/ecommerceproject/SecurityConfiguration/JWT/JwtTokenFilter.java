package com.rafeed.ecommerceproject.SecurityConfiguration.JWT;

import com.rafeed.ecommerceproject.Entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //custom filter chain
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //check if the request has Authorization header
        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        //validate the access token
        String accessToken = getAccessToken(request);
        if (!jwtTokenUtil.validateAccessToken(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        //set the authentication context
        setAuthenticationContext(accessToken, request);
        filterChain.doFilter(request, response);
    }


    //helper method to check if the request has Authorization header
    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        //check if header is empty or the given request starts with Bearer
        if (ObjectUtils.isEmpty((header)) || !header.startsWith("Bearer")) {
            return false;
        } else {
            return true;
        }
    }

    //helper method to extract and return access token from request
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String accessToken = header.split(" ")[1].trim();
        System.out.println("Access token: " + accessToken);
        return accessToken;
    }

    //helper method to set the authentication context
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                null
        );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    //helper method to extract and return the user details from the access token
    private UserDetails getUserDetails(String token) {
        User user = new User();
        String[] subjectArray = jwtTokenUtil.getSubject(token).split(",");
        user.setUserId(Integer.parseInt(subjectArray[0]));
        user.setEmail(subjectArray[1]);

        return user;
    }

}
