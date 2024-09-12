package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.address.Address;
import me.quyen.entities.student.dtos.UniversityDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDto implements Serializable {
    private String facultyName;
    private String facultyCode;
    private LocalDate establishedDate;
    private AddressDto address;
    private UniversityDto university;
}
