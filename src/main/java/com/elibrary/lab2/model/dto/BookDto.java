package com.elibrary.lab2.model.dto;

import com.elibrary.lab2.model.enumerations.Category;
import lombok.Data;


@Data
public class BookDto {

    private String name;

    private Category category;

    private Long author;

    private int availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
