package com.playermarket.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    Long id;
    Long teamId;
    String firstName;
    String lastName;
    LocalDate birthDate;
    LocalDate careerStart;
}
