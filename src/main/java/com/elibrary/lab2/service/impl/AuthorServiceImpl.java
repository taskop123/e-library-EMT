package com.elibrary.lab2.service.impl;

import com.elibrary.lab2.model.Author;
import com.elibrary.lab2.model.Country;
import com.elibrary.lab2.model.dto.AuthorDto;
import com.elibrary.lab2.model.exceptions.AuthorNotFoundException;
import com.elibrary.lab2.model.exceptions.CountryNotFoundException;
import com.elibrary.lab2.repository.AuthorRepository;
import com.elibrary.lab2.repository.CountryRepository;
import com.elibrary.lab2.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {

        return this.authorRepository.findAll();

    }

    @Override
    public Optional<Author> findById(Long id) {

        return this.authorRepository.findById(id);

    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Author> save(String name, String surname, Long country_id) {

        Country country = this.countryRepository.findById(country_id)
                .orElseThrow(() -> new CountryNotFoundException(country_id));

        this.authorRepository.deleteByName(name);

        Author author = new Author(name, surname, country);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    @Transactional
    public Optional<Author> save(AuthorDto authorDto) {

        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        this.authorRepository.deleteByName(authorDto.getName());

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);

        this.authorRepository.save(author);

        return Optional.of(author);

    }

    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String surname, Long country_id) {

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurname(surname);

        Country country = this.countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        author.setCountry(country);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(author.getName());
        author.setSurname(author.getSurname());

        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        author.setCountry(country);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {

        this.authorRepository.deleteById(id);

    }
}
