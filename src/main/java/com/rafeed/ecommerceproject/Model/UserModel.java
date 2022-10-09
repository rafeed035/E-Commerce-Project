package com.rafeed.ecommerceproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String matchingPassword;
}
