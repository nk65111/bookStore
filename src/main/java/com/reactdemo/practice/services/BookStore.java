package com.reactdemo.practice.services;


import java.util.List;
import com.reactdemo.practice.dao.BookReposatory;
import com.reactdemo.practice.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStore {
   
    @Autowired
    private BookReposatory bookReposatory;
    public  List<Book> getAllBook(){
        try{
        List<Book> books=(List<Book>)bookReposatory.findAll();
        return books;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Book getBookById(int id){
        Book book=bookReposatory.findById(id);
        return book;
    }

    public Book addNewBook(Book book){
        try{
         System.out.println(bookReposatory);
         Book b=bookReposatory.save(book);
        return b;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Book deleteBookById(int bid){
       Book b= getBookById(bid);
       bookReposatory.deleteById(bid);
       return b;
    }

    public void updateBookById(Book book,int bid){
       book.setId(bid);
       bookReposatory.save(book);
    }
}
