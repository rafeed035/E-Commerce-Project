package com.rafeed.ecommerceproject.Event.Listener;

import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Event.RegistrationCompleteEvent;
import com.rafeed.ecommerceproject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private UserService userService;

    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token, user);

        //send email to server
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        log.info("Click the link to verify you account: {}", url);
    }
}
