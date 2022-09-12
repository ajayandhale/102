package com.raktkosh.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAddress extends Address {
  
  @OneToOne
  @JoinColumn(name = "user_id")
  @MapsId
  private User user;
}
