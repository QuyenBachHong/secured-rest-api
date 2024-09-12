package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.address.Address;
import me.quyen.entities.student.dtos.CourseDto;
import me.quyen.entities.student.dtos.DepartmentDto;
import me.quyen.entities.student.dtos.PersonalDataDto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto implements Serializable {
    private String email;
    private boolean status;
    private int experiencedYear;
    private String academicDegree;
    private String scienceDegree;
    private String sciencePosition;
    private PersonalDataDto personalData;
    private AddressDto address;
    private DepartmentDto department;
    private Set<CourseDto> courses = new LinkedHashSet<>();
}
