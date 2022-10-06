package com.rafeed.ecommerceproject.CustomException.ExceptionHandler;

import com.rafeed.ecommerceproject.CustomException.ErrorMessage.EntityAlreadyExistsErrorMessage;
import com.rafeed.ecommerceproject.CustomException.ErrorMessage.EntityNotFoundErrorMessage;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotfoundException.class)
    public ResponseEntity<EntityNotFoundErrorMessage> entityNotFoundException(EntityNotfoundException entityNotfoundException,
                                                                              WebRequest webRequest){
        EntityNotFoundErrorMessage entityNotFoundErrorMessage = new EntityNotFoundErrorMessage(HttpStatus.NOT_FOUND,
                entityNotfoundException.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundErrorMessage);
    }


    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<EntityAlreadyExistsErrorMessage> entityAlreadyExistsException(EntityAlreadyExistsException entityAlreadyExistsException,
                                                                                        WebRequest webRequest){
        EntityAlreadyExistsErrorMessage entityAlreadyExistsErrorMessage = new EntityAlreadyExistsErrorMessage(HttpStatus.CONFLICT,
                entityAlreadyExistsException.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(entityAlreadyExistsErrorMessage);
    }
}
