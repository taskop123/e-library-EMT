package com.elibrary.lab2.repository;

import com.elibrary.lab2.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
    void deleteByName(String name);
    Page<Book> findAll(Pageable pageable);
}
