package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {

    @Autowired
    private JacksonTester<Book> jacksonTester;

    @Test
    void testSerialize() throws Exception{
        var book = Book.of("1234567890","title", "author", 9.01, "kms");
        var jsonContent= jacksonTester.write(book);

        assertThat(jsonContent).extractingJsonPathNumberValue("@.id")
                .isEqualTo(book.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
                .isEqualTo(book.publisher());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version")
                .isEqualTo(book.version());

    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "id": null,
                    "isbn": "1234567890",
                    "title": "minseok book",
                    "author": "minseok",
                    "price": 9.14,
                    "publisher": "kms",
                    "version": 0
                }
                """;

        assertThat(jacksonTester.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "minseok book", "minseok", 9.14, "kms"));
    }
}
