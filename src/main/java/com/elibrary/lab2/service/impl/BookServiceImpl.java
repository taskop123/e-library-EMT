package com.elibrary.lab2.service.impl;

import com.elibrary.lab2.model.Author;
import com.elibrary.lab2.model.Book;
import com.elibrary.lab2.model.dto.BookDto;
import com.elibrary.lab2.model.enumerations.Category;
import com.elibrary.lab2.model.exceptions.AuthorNotFoundException;
import com.elibrary.lab2.model.exceptions.BookNotFoundException;
import com.elibrary.lab2.repository.AuthorRepository;
import com.elibrary.lab2.repository.BookRepository;
import com.elibrary.lab2.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {

        return this.bookRepository.findAll();

    }

    @Override
    public Optional<Book> findById(Long id) {

        return this.bookRepository.findById(id);

    }

    @Override
    public Optional<Book> findByName(String name) {

        return this.bookRepository.findByName(name);

    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {

        return this.bookRepository.findAll(pageable);

    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Category category, Long author_id, int availableCopies) {

        Author author = this.authorRepository.findById(author_id)
                .orElseThrow(() -> new AuthorNotFoundException(author_id));

        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> save(BookDto bookDto) {

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Long author_id, int availableCopies) {

        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);

        Author author = this.authorRepository.findById(author_id)
                .orElseThrow(() -> new AuthorNotFoundException(author_id));

        book.setAuthor(author);

        this.bookRepository.save(book);

        return Optional.of(book);
    }


    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {

        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setAuthor(author);

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public void reserveBook(Long id) {

        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        int availableCopies = book.getAvailableCopies();

        if(availableCopies - 1 < 0){
            deleteById(id);
            return;
        }

        int newAvailableCopies = availableCopies - 1;

        book.setAvailableCopies(newAvailableCopies);

        this.bookRepository.save(book);

    }

    @Override
    public List<Category> listAllCategories() {

        List<Category> allCategories = new ArrayList<>();

        allCategories.add(Category.BIOGRAPHY);
        allCategories.add(Category.CLASSICS);
        allCategories.add(Category.DRAMA);
        allCategories.add(Category.FANTASY);
        allCategories.add(Category.HISTORY);
        allCategories.add(Category.NOVEL);
        allCategories.add(Category.THRILLER);

        return allCategories;

    }

    @Override
    public void deleteById(Long id) {

        this.bookRepository.deleteById(id);

    }
}
