package com.kameleoon.quote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VotingNotFoundException extends NotFoundException {
    public VotingNotFoundException(String message) {
        super(message);
    }
}
