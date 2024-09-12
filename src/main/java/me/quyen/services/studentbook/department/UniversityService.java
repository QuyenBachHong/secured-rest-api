package me.quyen.services.studentbook.department;

import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.University;
import me.quyen.entities.student.converter.UniversityFacultyDepartmentConverter;
import me.quyen.entities.student.dtos.UniversityDto;
import me.quyen.repositories.student.UniversityRepo;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UniversityService{
    final UniversityRepo universityRepo;


    public Stream<UniversityDto> findUniversities() {
        return universityRepo.findUniversities().stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Stream<UniversityDto> findByUniversityIds(Iterable<Long> universityIds) {
        return universityRepo.findByUniversityIds(universityIds)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Optional<UniversityDto> findByUniversityId(Long universityId) {
        return universityRepo.findByUniversityId(universityId)
                .map(UniversityFacultyDepartmentConverter::universityDto);

    }


    public Stream<UniversityDto> findByUniversityNameContaining(String universityName) {
        return universityRepo.findByUniversityNameContaining(universityName)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Stream<UniversityDto> findByUniversityCodeContaining(String universityCode) {
        return universityRepo.findByUniversityCodeContaining(universityCode)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Stream<UniversityDto> findByUniversityEmailContaining(String universityEmail) {
        return universityRepo.findByUniversityEmailContaining(universityEmail)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Stream<UniversityDto> findByUniversityCountryNameContaining(String countryName) {
        return universityRepo.findByUniversityCountryNameContaining(countryName)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Stream<UniversityDto> findByCountryNameAndStateNameContaining(String countryName, String state) {
        return universityRepo.findByCountryNameAndStateNameContaining(countryName,state)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }


    public Stream<UniversityDto> findByStateNameAndZipCodeContaining(String state, String zipCode) {
        return universityRepo.findByStateNameAndZipCodeContaining(state,zipCode)
                .stream().parallel()
                .map(UniversityFacultyDepartmentConverter::universityDto);
    }
}
