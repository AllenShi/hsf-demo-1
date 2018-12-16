package net.yarn.hsf.demo1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handle(RuntimeException e) {
    	
    	log.error("The exception is handled for {}", e.getMessage());
        
    	ErrorMessage errorMsg = new ErrorMessage();
    	errorMsg.setErrorCode(0);
    	errorMsg.setDescription(e.getLocalizedMessage());
    	errorMsg.setRootCause(e.getCause().getLocalizedMessage());
    	errorMsg.setResolution("You need contact your admin");
    	

        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
