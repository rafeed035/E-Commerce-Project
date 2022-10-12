package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Entity.VerificationToken;
import com.rafeed.ecommerceproject.Model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationToken(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User getUserByEmail(String email) throws EntityNotfoundException;

    void createPasswordResetTokenForUser(User user, String token);

    String validateResetPasswordToken(String token);

    User getUserByPasswordToken(String token) throws EntityNotfoundException;

    void changePassword(User user, String newPassword);
}
