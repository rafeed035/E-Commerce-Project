package com.rafeed.ecommerceproject.Controller;

import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Event.RegistrationCompleteEvent;
import com.rafeed.ecommerceproject.Model.UserModel;
import com.rafeed.ecommerceproject.Service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;

    public UserController(UserService userService,
                          ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("/userRegister")
    public String registerUser(@RequestBody UserModel userModel,
                               HttpServletRequest httpServletRequest){
        User user = userService.registerUser(userModel);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(httpServletRequest)
        ));
        return "Successfully registered";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User verified successfully";
        }
        else{
            return "Verification not successful";
        }
    }

    private String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://" +
                httpServletRequest.getServerName()+
                ":" +
                httpServletRequest.getServerPort() +
                "/api/user" +
                httpServletRequest.getContextPath();
    }
}
