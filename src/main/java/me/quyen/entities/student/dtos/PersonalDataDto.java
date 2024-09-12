package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataDto implements Serializable {
    private String fullName;
    private String citizenCode;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String gender;
    private String phoneNumber;
    private BigDecimal creditLimit;

}
