package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        bookRepository.deleteAll();
        //var book1 = new Book("1234567891", "Test", "Lyra", 9.91);
        var book1 = Book.of("1234567891", "Test", "Lyra", 9.91, "kms");
        //var book2 = new Book("1234567892", "Test book", "Polar", 9.94);
        var book2 = Book.of("1234567892", "Test book", "Polar", 9.94, "kms");
//        bookRepository.save(book1);
//        bookRepository.save(book2);
        bookRepository.saveAll(List.of(book1,book2));
    }
}
