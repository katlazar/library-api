package com.example.libraryapi.repository;

import com.example.libraryapi.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    @Query("SELECT a FROM BookModel a WHERE LOWER(a.author) = LOWER(:authorParam)")
    List<BookModel> listBookByAuthor(@Param("authorParam") String authorParam);

    @Query("SELECT t FROM BookModel t WHERE LOWER(t.title) LIKE LOWER(concat('%', :titleFragment, '%'))")
    List<BookModel> listBookByTitleFragment(@Param("titleFragment") String titleFragment);

    @Query("SELECT y FROM BookModel y WHERE y.yearRelease > :yearParam")
    List<BookModel> listBookAfterYear(@Param("yearParam") Integer yearParam);

    List<BookModel> findByYearReleaseLessThan(Integer year);

    List<BookModel> findByAuthorStartingWithIgnoreCase(String letter);

}
