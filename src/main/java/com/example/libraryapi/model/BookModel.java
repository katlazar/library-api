package com.example.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "year_release")
    private Integer yearRelease;

}
