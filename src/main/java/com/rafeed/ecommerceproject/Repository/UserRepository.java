package com.rafeed.ecommerceproject.Repository;

import com.rafeed.ecommerceproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUserId(int userId);
    User getUserByFirstName(String firstName);
    User getUserByLastName(String lastName);
    User getUserByFirstNameAndLastName(String firstName, String lastName);
}
