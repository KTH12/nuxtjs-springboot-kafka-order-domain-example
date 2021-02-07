package com.orderserver.order;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderSheet {
    Long orderId;
    String productName;
    Integer qty;
    Boolean isConfirm;
}
