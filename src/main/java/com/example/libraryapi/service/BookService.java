package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.model.BookModel;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.utils.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getBookList() {
        return bookRepository.findAll().stream().map(BookMapper::toBookDto).toList();
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id).map(BookMapper::toBookDto).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookDto addBook(BookDto book) {
        BookModel newBook = BookMapper.toBookModel(book);
        newBook.setId(null);
        return BookMapper.toBookDto(bookRepository.save(newBook));
    }

    public BookDto updateBook(BookDto book) {
        return BookMapper.toBookDto(bookRepository.save(BookMapper.toBookModel(book)));
    }

    public void deleteBook(Long id) {
        boolean isBook = bookRepository.existsById(id);
        if (!isBook) {
            throw new EntityNotFoundException("Id not found: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookDto> getBooksByAuthor(String author) {
        return bookRepository.listBookByAuthor(author).stream().map(BookMapper::toBookDto).toList();
    }

    public List<BookDto> getBooksByTitle(String title) {
        return bookRepository.listBookByTitleFragment(title).stream().map(BookMapper::toBookDto).toList();
    }

    public List<BookDto> getBooksAfterYear(Integer year) {
        return bookRepository.listBookAfterYear(year).stream().map(BookMapper::toBookDto).toList();
    }

    public List<BookDto> getBooksBeforeYear(Integer year) {
        return bookRepository.findByYearReleaseLessThan(year).stream().map(BookMapper::toBookDto).toList();
    }

    public List<BookDto> getBooksByAuthorFirstLetter(String letter) {
        return bookRepository.findByAuthorStartingWithIgnoreCase(letter).stream().map(BookMapper::toBookDto).toList();
    }
}
