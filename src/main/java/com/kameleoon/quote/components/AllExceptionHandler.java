package com.kameleoon.quote.components;

import com.kameleoon.quote.exceptions.AlreadyVotesException;
import com.kameleoon.quote.exceptions.QuoteNotFoundException;
import com.kameleoon.quote.exceptions.UserAlreadyExists;
import com.kameleoon.quote.exceptions.VotingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler({UserAlreadyExists.class, AlreadyVotesException.class})
    public ResponseEntity<Object> handlerAlreadyExceptions(Exception exception) {
        log.info(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameNotFoundException.class, QuoteNotFoundException.class, VotingNotFoundException.class})
    public ResponseEntity<Object> NotFoundHandle(Exception exception) {
        log.info(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
