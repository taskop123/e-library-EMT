package com.elibrary.lab2.model.dto;

import com.elibrary.lab2.model.Country;
import lombok.Data;

import javax.persistence.OneToOne;

@Data
public class AuthorDto {

    private String name;

    private String surname;

    private Long country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
