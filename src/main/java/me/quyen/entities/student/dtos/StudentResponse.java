package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.PersonalData;
import me.quyen.entities.student.Student;
import me.quyen.entities.student.address.Address;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse implements Serializable {
    private String email;
    private boolean status;
    private boolean isSenior;
    private String gradeLevel;
    private String studentCode;
    private Address address;
    private PersonalData personalData;
    public static StudentResponse toStudentResponse(Student student){
        return Optional.ofNullable(student).stream().map(src -> {
            StudentResponse dsc = new StudentResponse();
            BeanUtils.copyProperties(src,dsc);
            dsc.setPersonalData(src.getPersonalData());
            dsc.setAddress(src.getAddress());
            return dsc;
        }).findFirst().orElse(null);
    }
}
