package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Entity.VerificationToken;
import com.rafeed.ecommerceproject.Model.UserModel;
import com.rafeed.ecommerceproject.Repository.UserRepository;
import com.rafeed.ecommerceproject.Repository.VerificationTokenRepository;
import com.rafeed.ecommerceproject.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository verificationTokenRepository;

    public UserServiceImplementation(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setAddress(userModel.getAddress());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return user;
    }

    @Override
    public User saveUser(User user) {

        return null;
    }

    @Override
    public void saveVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime()
                - calendar.getTime().getTime()) <= 0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setActive(true);
        userRepository.save(user);
        return "valid";
    }
}
