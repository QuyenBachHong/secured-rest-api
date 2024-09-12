package me.quyen.entities.student.converter;

import me.quyen.entities.student.PersonalData;
import me.quyen.entities.student.dtos.PersonalDataDto;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class PersonalDataConverter
{
    public static PersonalDataDto personalDataDto(PersonalData personalData){
        return Optional.ofNullable(personalData)
                .stream().map(src -> {
                    PersonalDataDto dsc = new PersonalDataDto();
                    BeanUtils.copyProperties(src,dsc);
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static PersonalData personalData(PersonalDataDto personalDataDto){
        return Optional.ofNullable(personalDataDto)
                .stream().map(src -> {
                    PersonalData dsc = new PersonalData();
                    BeanUtils.copyProperties(src,dsc);
                    return dsc;
                }).findFirst().orElse(null);
    }
}
