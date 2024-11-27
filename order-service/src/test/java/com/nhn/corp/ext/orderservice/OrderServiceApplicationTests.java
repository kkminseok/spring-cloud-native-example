package com.nhn.corp.ext.orderservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhn.corp.ext.orderservice.book.Book;
import com.nhn.corp.ext.orderservice.book.BookClient;
import com.nhn.corp.ext.orderservice.order.domain.Order;
import com.nhn.corp.ext.orderservice.order.event.OrderAcceptedMessage;
import com.nhn.corp.ext.orderservice.order.web.OrderRequest;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.BeforeAll;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestChannelBinderConfiguration.class)
@Testcontainers
class OrderServiceApplicationTests {

//    // Customer
//    private static KeycloakToken bjornTokens;
//    // Customer and employee
//    private static KeycloakToken isabelleTokens;
//
//    @Autowired
//    private OutputDestination output;
//
//    @Container
//    private static final KeycloakContainer keycloakContainer = new KeycloakContainer("quay.io/keycloak/keycloak:24.0")
//            .withRealmImportFile("/test-realm-config.json");
//
//    // postgresql 내 로컬에서는 no space left뜨면서 안됨. 향후 수정필요.
//    @Container
//    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:14.12");
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @MockBean
//    private BookClient bookClient;
//
//    @DynamicPropertySource
//    static void postgresqlProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.r2dbc.url", OrderServiceApplicationTests::r2dbcUrl);
//        registry.add("spring.r2dbc.username", postgresql::getUsername);
//        registry.add("spring.r2dbc.password", postgresql::getPassword);
//        registry.add("spring.flyway.url", postgresql::getJdbcUrl);
//
//        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri",
//                () -> keycloakContainer.getAuthServerUrl() + "/realms/PolarBookshop");
//    }
//
//    private static String r2dbcUrl() {
//        return String.format("r2dbc:postgresql://%s:%s/%s", postgresql.getHost(),
//                postgresql.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT), postgresql.getDatabaseName());
//    }
//
//    @BeforeAll
//    static void generateAccessTokens() {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(keycloakContainer.getAuthServerUrl() + "/realms/PolarBookshop/protocol/openid-connect/token")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .build();
//
//        isabelleTokens = authenticateWith("isabelle", "password", webClient);
//        bjornTokens = authenticateWith("bjorn", "password", webClient);
//    }
//
//    @Test
//    void whenGetOwnOrdersThenReturn() throws IOException {
//        String bookIsbn = "1231231231";
//        Book book = new Book(bookIsbn, "Title", "Author", 1.0);
//        given(bookClient.getBookByIsbn(bookIsbn)).willReturn(Mono.just(book));
//        OrderRequest orderRequest = new OrderRequest(bookIsbn, 1);
//
//        Order expectedOrder = webTestClient.post().uri("/orders")
//                .headers(headers -> headers.setBearerAuth(bjornTokens.accessToken()))
//                .bodyValue(orderRequest)
//                .exchange()
//                .expectStatus().is2xxSuccessful()
//                .expectBody(Order.class).returnResult().getResponseBody();
//
//        assertThat(expectedOrder).isNotNull();
//        assertThat(objectMapper.readValue(output.receive().getPayload(), OrderAcceptedMessage.class))
//                .isEqualTo(new OrderAcceptedMessage(expectedOrder.id()));
//
//        webTestClient.get().uri("/orders")
//                .headers(headers -> headers.setBearerAuth(bjornTokens.accessToken()))
//                .exchange()
//                .expectStatus().is2xxSuccessful()
//                .expectBodyList(Order.class).value(orders -> {
//                    List<Long> orderIds = orders.stream()
//                            .map(Order::id)
//                            .collect(Collectors.toList());
//                    assertThat(orderIds).contains(expectedOrder.id());
//                });
//    }
//
//
//    private static KeycloakToken authenticateWith(String username, String password, WebClient webClient) {
//        return webClient
//                .post()
//                .body(BodyInserters.fromFormData("grant_type", "password")
//                        .with("client_id", "polar-test")
//                        .with("username", username)
//                        .with("password", password)
//                )
//                .retrieve()
//                .bodyToMono(KeycloakToken.class)
//                .block();
//    }
//
//    private record KeycloakToken(String accessToken) {
//
//        @JsonCreator
//        private KeycloakToken(@JsonProperty("access_token") final String accessToken) {
//            this.accessToken = accessToken;
//        }
//
//    }
}
