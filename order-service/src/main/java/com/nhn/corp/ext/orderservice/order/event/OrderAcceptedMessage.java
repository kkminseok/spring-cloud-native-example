package com.nhn.corp.ext.orderservice.order.event;

public record OrderAcceptedMessage(
        Long orderId
) {}
