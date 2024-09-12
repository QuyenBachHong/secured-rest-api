package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.address.Address;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable {
    private String email;
    private boolean status;
    private boolean isSenior;
    private String gradeLevel;
    private String studentCode;
    private PersonalDataDto personalData;
    private AddressDto address;
    private DepartmentDto department;
    private Set<CourseDto> courses = new LinkedHashSet<>();
    private Set<TeacherDto> teachers = new LinkedHashSet<>();
    private Set<BookDto> books  = new LinkedHashSet<>();

}
