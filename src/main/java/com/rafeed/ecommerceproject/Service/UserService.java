package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.Entity.User;
import com.rafeed.ecommerceproject.Model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
    User saveUser(User user);

    void saveVerificationToken(String token, User user);

    String validateVerificationToken(String token);
}
