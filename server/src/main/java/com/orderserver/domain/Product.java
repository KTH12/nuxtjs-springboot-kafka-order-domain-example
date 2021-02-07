package com.orderserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue
  @Column(name = "PRODUCT_ID")
  private Long id;
 
  @NotNull
  @Valid
  private String name;

  @NotNull
  @Valid
  private Integer price;

  @ManyToOne
  @JoinColumn(name = "PROVIDER_ID")
  @JsonBackReference  // 순환참조 방지
  private Provider provider;
}
