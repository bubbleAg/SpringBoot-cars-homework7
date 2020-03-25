package com.koro.carshomework7.exception;

import com.koro.carshomework7.controller.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@CrossOrigin
public class ErrorHandler {

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage carNotFoundExceptionHandler(CarNotFoundException ex) {
        ResponseMessage responseMsg = new ResponseMessage(ex.getMessage());
        return responseMsg;
    }
}
