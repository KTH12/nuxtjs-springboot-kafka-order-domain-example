package com.orderserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provider {

  @Id
  @GeneratedValue
  @Column(name = "PROVIDER_ID")
  private Long id;

  @NotNull
  @Valid
  @Column(name = "NAME")
  private String name;

  @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
  @JsonBackReference // 순환참조 방지
  private List<Product> products = new ArrayList<Product>();
}
