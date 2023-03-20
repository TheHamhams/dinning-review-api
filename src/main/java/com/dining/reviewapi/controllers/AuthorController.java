package com.dining.reviewapi.controllers;

import com.dining.reviewapi.exceptions.AuthorUsernameNotFoundException;
import com.dining.reviewapi.exceptions.UsernameAlreadyExistsException;
import com.dining.reviewapi.models.Author;
import com.dining.reviewapi.repositories.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

//    GET
    @GetMapping()
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(this.authorRepository.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("username") String username) {
        Author author = this.authorRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorUsernameNotFoundException("Username was not found"));

        return ResponseEntity.ok(author);
    }

//    POST
    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author request) {
        Optional<Author> authorOptional = this.authorRepository.findByUsername(request.getUsername());

        if (authorOptional.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        Author author = this.authorRepository.save(request);

        return ResponseEntity.ok(author);
    }

//    PUT
    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        Author updatedAuthor = this.authorRepository.findByUsername(author.getUsername())
                .orElseThrow(() -> new AuthorUsernameNotFoundException("Username was not found"));

        if (author.getCity() != null) {
            updatedAuthor.setCity(author.getCity());
        }

        if (author.getState() != null) {
            updatedAuthor.setState(author.getState());
        }

        if (author.getZipcode() != null) {
            updatedAuthor.setZipcode(author.getZipcode());
        }

        if (author.getPeanutAllergy() != null) {
            updatedAuthor.setPeanutAllergy(author.getPeanutAllergy());
        }

        if (author.getEggAllergy() != null) {
            updatedAuthor.setEggAllergy(author.getEggAllergy());
        }

        if (author.getDairyAllergy() != null) {
            updatedAuthor.setDairyAllergy(author.getDairyAllergy());
        }

        this.authorRepository.save(updatedAuthor);
        return ResponseEntity.ok(updatedAuthor);
    }

//    DELETE
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("username") String username) {
        Author author = this.authorRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorUsernameNotFoundException("Username not found"));

        this.authorRepository.delete(author);
        return ResponseEntity.ok(username + " deleted");
    }
}
