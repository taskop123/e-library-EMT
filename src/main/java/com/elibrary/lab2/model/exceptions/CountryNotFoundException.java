package com.elibrary.lab2.model.exceptions;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(Long country_id) {
        super(String.format("Country with id: %d was not found!", country_id));
    }
}
