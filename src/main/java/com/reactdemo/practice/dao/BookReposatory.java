package com.reactdemo.practice.dao;

import com.reactdemo.practice.entities.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReposatory extends CrudRepository<Book,Integer> {
    public Book findById(int id);
}
