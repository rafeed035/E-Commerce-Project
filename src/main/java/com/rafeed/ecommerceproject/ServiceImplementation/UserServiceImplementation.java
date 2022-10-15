package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.PasswordResetToken;
import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Entity.VerificationToken;
import com.rafeed.ecommerceproject.Model.UserModel;
import com.rafeed.ecommerceproject.Repository.PasswordResetTokenRepository;
import com.rafeed.ecommerceproject.Repository.UserRepository;
import com.rafeed.ecommerceproject.Repository.VerificationTokenRepository;
import com.rafeed.ecommerceproject.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository verificationTokenRepository;
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public UserServiceImplementation(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     VerificationTokenRepository verificationTokenRepository,
                                     PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
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

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.getVerificationTokenByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User getUserByEmail(String email) throws EntityNotfoundException {
        User user = userRepository.getUserByEmail(email);
        if(user == null){
            throw new EntityNotfoundException("User not found!");
        }
        return user;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validateResetPasswordToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.getPasswordResetTokenByToken(token);
        if(passwordResetToken == null){
            return "invalid";
        }

        Calendar calendar = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime()
                - calendar.getTime().getTime()) <= 0){
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    @Override
    public User getUserByPasswordToken(String token) throws EntityNotfoundException {
        User user = passwordResetTokenRepository.getPasswordResetTokenByToken(token).getUser();
        if(user == null){
            throw new EntityNotfoundException("User not found!");
        }
        return user;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
