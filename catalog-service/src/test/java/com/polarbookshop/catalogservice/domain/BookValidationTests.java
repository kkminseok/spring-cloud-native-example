package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book(null, "1234567890", "Title", "Author", 9.90, "kms", null, null, 1);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = new Book(null, "a2345768", "title", "author", 9.98, "kms", null, null, 1);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid");
    }
}
