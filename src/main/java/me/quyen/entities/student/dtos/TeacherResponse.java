package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.PersonalData;
import me.quyen.entities.student.Teacher;
import me.quyen.entities.student.address.Address;
import me.quyen.entities.student.converter.PersonalDataConverter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse implements Serializable {
    private String email;
    private boolean status;
    private int experiencedYear;
    private String academicDegree;
    private String scienceDegree;
    private String sciencePosition;
    private Address address;
    private PersonalData personalData;
    public static TeacherResponse toTeacherResponse(Teacher teacher){
        return Optional.ofNullable(teacher).stream().map(src -> {
            TeacherResponse dsc = new TeacherResponse();
            BeanUtils.copyProperties(src,dsc);
            dsc.setPersonalData(src.getPersonalData());
            dsc.setAddress(src.getAddress());
            return dsc;
        }).findFirst().orElse(null);
    }
}
