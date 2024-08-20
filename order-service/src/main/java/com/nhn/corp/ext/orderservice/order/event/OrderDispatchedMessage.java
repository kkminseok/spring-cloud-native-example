package com.nhn.corp.ext.orderservice.order.event;

public record OrderDispatchedMessage (
        Long orderId
){ }
