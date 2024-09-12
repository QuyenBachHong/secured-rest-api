package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDto implements Serializable {
    private String universityName;
    private String universityCode;
    private String universityPrefix;
    private String universitySuffix;
    private AddressDto address;
}
