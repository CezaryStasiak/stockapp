package cs.stockapp.exceptionhandling;

import cs.stockapp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.sql.SQLException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({SQLException.class, ConnectException.class})
    public ResponseEntity<String> sqlExceptionHandler(){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Database error");
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> userNotFoundException(){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("User not found");
    }


}
