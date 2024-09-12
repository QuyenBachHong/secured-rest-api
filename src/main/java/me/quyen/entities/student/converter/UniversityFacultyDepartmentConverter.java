package me.quyen.entities.student.converter;

import me.quyen.entities.student.Department;
import me.quyen.entities.student.Faculty;
import me.quyen.entities.student.University;
import me.quyen.entities.student.dtos.DepartmentDto;
import me.quyen.entities.student.dtos.FacultyDto;
import me.quyen.entities.student.dtos.UniversityDto;
import me.quyen.repositories.student.FacultyRepo;
import me.quyen.repositories.student.UniversityRepo;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class UniversityFacultyDepartmentConverter {
    public static UniversityDto universityDto(University university){
        return Optional.ofNullable(university)
                .stream().map(src -> {
                    UniversityDto dsc = new UniversityDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static University university(UniversityDto universityDto){
        return Optional.ofNullable(universityDto).stream().map(src -> {
            University dsc = new University();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            return dsc;
        }).findFirst().orElse(null);
    }
    public static FacultyDto facultyDto(Faculty faculty){
        return Optional.ofNullable(faculty)
                .stream().map(src -> {
                    FacultyDto dsc = new FacultyDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    dsc.setUniversity(
                            UniversityFacultyDepartmentConverter.universityDto(src.getUniversity())
                    );
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static Faculty faculty(FacultyDto facultyDto, UniversityRepo universityRepo){
        return Optional.ofNullable(facultyDto).stream().map(src -> {
            Faculty dsc = new Faculty();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            Optional.ofNullable(src.getUniversity()).ifPresent(universityDto -> {
                University university
                        = UniversityFacultyDepartmentConverter.university(universityDto);
                universityRepo.save(university);
                dsc.addAssociation(university);
            });
            return dsc;
        }).findFirst().orElse(null);
    }
    public static DepartmentDto departmentDto(Department department){
        return Optional.ofNullable(department)
                .stream().map(src -> {
                    DepartmentDto dsc = new DepartmentDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    dsc.setFaculty(
                            UniversityFacultyDepartmentConverter.facultyDto(src.getFaculty())
                    );
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static Department department(DepartmentDto departmentDto
            , UniversityRepo universityRepo, FacultyRepo facultyRepo){
        return Optional.ofNullable(departmentDto).stream().map(src -> {
            Department dsc = new Department();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            Optional.ofNullable(src.getFaculty())
                    .ifPresent(facultyDto ->{
                        Faculty faculty = UniversityFacultyDepartmentConverter.faculty(facultyDto, universityRepo);
                        Optional.ofNullable(faculty).ifPresent(f -> {
                            facultyRepo.save(f);
                            dsc.addAssociation(f);
                        });
                    });

            return dsc;
        }).findFirst().orElse(null);
    }
}
