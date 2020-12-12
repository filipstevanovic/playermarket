package com.playermarket.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Long id;
    private Long teamId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate careerStart;
}
