package com.raktkosh.dto.requests;

import com.raktkosh.core.Antigens;
import com.raktkosh.core.BloodTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BloodBankRepositoryIdDTO {
  protected Long bankId;
  protected BloodTypes type;
  protected Antigens antigen;
}
