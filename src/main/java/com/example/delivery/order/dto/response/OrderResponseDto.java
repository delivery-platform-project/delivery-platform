package com.example.delivery.order.dto.response;

import com.example.delivery.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private UUID orderId;
    private String dStreetAddress;
    private String dDetailAddress;
    private String requirements;
    private int totalCount;
    private int totalPrice;
    private String orderStatus;
//    private Long userId;
    private UUID storeId;

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .dStreetAddress(order.getDStreetAddress())
                .dDetailAddress(order.getDDetailAddress())
                .requirements(order.getRequirements())
                .totalCount(order.getTotalCount())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus().getLabel())
//                .userId(order.getUser().getUserId())
                .storeId(order.getStore().getStoreId())
                .build();
    }
}

