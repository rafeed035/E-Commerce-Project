package com.rafeed.ecommerceproject.CustomException.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityNotFoundErrorMessage {
    private HttpStatus httpStatus;
    private String message;
}
