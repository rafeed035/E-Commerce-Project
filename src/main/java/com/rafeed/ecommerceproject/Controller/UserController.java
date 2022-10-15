package com.rafeed.ecommerceproject.Controller;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Entity.VerificationToken;
import com.rafeed.ecommerceproject.Event.RegistrationCompleteEvent;
import com.rafeed.ecommerceproject.Model.PasswordModel;
import com.rafeed.ecommerceproject.Model.UserModel;
import com.rafeed.ecommerceproject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
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

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest httpServletRequest){
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(httpServletRequest), verificationToken);
        return "Verification Link Sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel,
                                HttpServletRequest httpServletRequest) throws EntityNotfoundException {
        User user = userService.getUserByEmail(passwordModel.getEmail());
        String token = UUID.randomUUID().toString();
        String url = "";
        userService.createPasswordResetTokenForUser(user, token);
        url = passwordResetTokenMail(user, applicationUrl(httpServletRequest), token);

        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordModel passwordModel) throws EntityNotfoundException {
        String result = userService.validateResetPasswordToken(token);
        if(!result.equalsIgnoreCase("valid")){
            return "Invalid token";
        }
        User user = userService.getUserByPasswordToken(token);
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password changed successfully";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel) throws EntityNotfoundException {
        User user = userService.getUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidPassword(user, passwordModel.getOldPassword())){
            return "Invalid old password";
        }
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password changed successfully";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword?token=" + token;
        log.info("Click the link to reset your password: {}", url);
        return url;
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        log.info("Click the link to verify your account: {}", url);
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
