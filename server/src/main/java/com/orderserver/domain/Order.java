package com.orderserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @NotNull
    @Valid
    @Column(name = "provider_id")
    private Long providerId;

    @NotNull
    @Valid
    private Integer qty;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference  // 순환참조 방지
    private Product product;

    @NotNull
    @Valid
    @Column(name = "provider_name")
    private String providerName;

    private Boolean confirm;
}
