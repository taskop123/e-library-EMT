package com.elibrary.lab2.service;

import com.elibrary.lab2.model.Book;
import com.elibrary.lab2.model.dto.BookDto;
import com.elibrary.lab2.model.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Page<Book> findAllWithPagination(Pageable pageable);

    Optional<Book> save(String name, Category category, Long author_id, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long author_id, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

}
