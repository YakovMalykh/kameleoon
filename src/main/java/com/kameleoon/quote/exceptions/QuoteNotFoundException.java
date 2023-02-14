package com.kameleoon.quote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuoteNotFoundException extends NotFoundException {
    public QuoteNotFoundException(String message) {
        super(message);
    }
}
