package com.nhn.corp.ext.dispatcherservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
public class DispatchingFunctionsIntegrationTests {

//    @Autowired
//    private FunctionCatalog catalog;
//
//    @Test
//    void packAndLabelOrder() {
//        Function<OrderAcceptedMessage, Flux<OrderDispatcherMessage>> packAndLabel = catalog.lookup(
//                Function.class,
//            "pack|label"); // FunctionCatalog로 부터 합성 함수를 가져옴
//
//        long orderId = 727;
//
//        StepVerifier.create(packAndLabel.apply(
//                new OrderAcceptedMessage(orderId) // 함수에 대한 입력인 OrderAcceptedMessage 정의
//        ))
//                .expectNextMatches(dispatchedOrder -> dispatchedOrder.equals(new OrderDispatcherMessage(orderId)))
//                .verifyComplete();// 함수 출력의 객체가 OrderDispatcherMessage인지 확인
//    }
}