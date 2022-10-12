package com.rafeed.ecommerceproject.Repository;

import com.rafeed.ecommerceproject.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken getPasswordResetTokenByToken(String token);
}
