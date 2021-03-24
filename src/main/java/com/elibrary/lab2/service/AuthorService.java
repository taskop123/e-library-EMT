package com.elibrary.lab2.service;

import com.elibrary.lab2.model.Author;
import com.elibrary.lab2.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> findByName(String name);

    Optional<Author> save(String name, String surname, Long country);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, String name, String surname, Long country);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);

}
