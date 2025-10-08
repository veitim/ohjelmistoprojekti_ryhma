package com.example.ticketguru.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RooliNotFoundException extends RuntimeException {

    public RooliNotFoundException(Long id) {
        super("Roolia Id=" + id + " ei löytynyt");
    }
}
