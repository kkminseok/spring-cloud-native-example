//package com.polarbookshop.catalogservice.web;
//
//import com.polarbookshop.catalogservice.domain.Book;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//class BookControllerTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    void whenPostRequestThenBookCreate() {
//        var expectedBook = Book.of( "1231231230", "title", "author", 9.54, "kms");
//
//        webTestClient
//                .post()
//                .uri("/books")
//                .bodyValue(expectedBook)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Book.class).value(actualBook -> {
//                    assertThat(actualBook).isNotNull();
//                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
//                });
//    }
//}