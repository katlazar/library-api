package com.example.libraryapi.utils.mapper;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.model.BookModel;

public class BookMapper {

    public static BookModel toBookModel(BookDto bookDto) {
        BookModel bookModel = new BookModel();
        bookModel.setId(bookDto.getId());
        bookModel.setTitle(bookDto.getTitle());
        bookModel.setAuthor(bookDto.getAuthor());
        bookModel.setYearRelease(bookDto.getYearRelease());
        return bookModel;
    }

    public static BookDto toBookDto(BookModel bookModel) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(bookModel.getTitle());
        bookDto.setAuthor(bookModel.getAuthor());
        bookDto.setYearRelease(bookModel.getYearRelease());
        bookDto.setId(bookModel.getId());
        return bookDto;
    }
}
