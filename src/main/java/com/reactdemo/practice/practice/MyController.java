package com.reactdemo.practice.practice;

import java.util.List;
import java.util.Optional;

import com.reactdemo.practice.entities.Book;
import com.reactdemo.practice.services.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
   
    @Autowired
    private BookStore bookStore;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books= bookStore.getAllBook();
        if(books==null||books.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(books));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
       Book book=bookStore.getBookById(id);
       if(book==null){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.ok().body(book);
    }
    
    @PostMapping("/books")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book){
        try{
            Book b= bookStore.addNewBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/books/{bid}")
    public ResponseEntity<Book> deleteBookById(@PathVariable("bid") int bookid){
       
        Book b=bookStore.deleteBookById(bookid);
        if(b==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(b));
    }
    
    @PutMapping("/books/{bid}")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book,@PathVariable("bid") int bid){
      try{  
        bookStore.updateBookById(book,bid);
        return ResponseEntity.of(Optional.of(book));
      }catch(Exception e){
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
      }
    }

    @GetMapping("/")
    @ResponseBody
    public String home(){
        return "Welcome in book Store";
    }
    
}
