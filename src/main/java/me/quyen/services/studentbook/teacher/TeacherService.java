package me.quyen.services.studentbook.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.*;
import me.quyen.entities.student.converter.StudentTeacherCourseConverter;
import me.quyen.entities.student.dtos.TeacherDto;
import me.quyen.entities.student.dtos.TeacherResponse;
import me.quyen.repositories.student.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TeacherService {
    final UniversityRepo universityRepo;
    final FacultyRepo facultyRepo;
    final DepartmentRepo departmentRepo;
    final CourseRepo courseRepo;
    final StudentTeacherRepo studentTeacherRepo;
    final TeacherCourseRepo teacherCourseRepo;
    final TeacherRepo teacherRepo;

    public Stream<TeacherResponse> findAllTeachers() {
        return teacherRepo.findAllTeachers()
                .stream().parallel()
                .map(teacher -> TeacherResponse.toTeacherResponse(teacher));
    }

    public Optional<TeacherDto> findByTeacherId(Long teacherId) {
        return teacherRepo.findByTeacherId(teacherId)
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByTeacherEmail(String teacherEmail) {
        return teacherRepo.findByTeacherEmail(teacherEmail)
                .stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByTeacherStatus(Boolean teacherStatus) {
        return teacherRepo.findByTeacherStatus(teacherStatus)
                .stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByTeacherEmailAndStatus(
            String teacherEmail, Boolean teacherStatus) {
        return teacherRepo.findByTeacherEmailAndStatus(teacherEmail, teacherStatus)
                .stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByTeacherFirstNameAndLastName(
            String firstName, String lastName) {
        return teacherRepo.findByTeacherFirstNameAndLastName(firstName, lastName)
                .stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByPersonalData_FullNameContaining(String teacherFullName) {
        return teacherRepo.findByPersonalData_FullNameContaining(teacherFullName)
                .stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByCitizenCode(String citizenCode) {
        return teacherRepo.findByCitizenCode(citizenCode)
                .stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByDepartmentName(String departmentName) {
        return teacherRepo.findByTeacherIds(
                        departmentRepo.findByDepartmentName(departmentName)
                                .stream().parallel()
                                .map(Department::getTeachers)
                                .flatMap(Collection::parallelStream)
                                .map(Teacher::getId).collect(Collectors.toSet())
                ).stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }


    public Stream<TeacherDto> findByFacultyName(String facultyName) {
        return teacherRepo.findByTeacherIds(facultyRepo.findByFacultyNameContaining(facultyName)
                        .stream().parallel()
                        .map(Faculty::getDepartments)
                        .flatMap(Collection::parallelStream)
                        .map(Department::getTeachers)
                        .flatMap(Collection::parallelStream)
                        .map(Teacher::getId).collect(Collectors.toSet())
                ).stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }

    public Stream<TeacherDto> findByUniversityName(String universityName) {
        return teacherRepo.findByTeacherIds(
                universityRepo.findByUniversityNameContaining(universityName)
                        .stream().parallel()
                        .map(University::getFaculties).flatMap(Collection::parallelStream)
                        .map(Faculty::getDepartments).flatMap(Collection::parallelStream)
                        .map(Department::getTeachers).flatMap(Collection::parallelStream)
                        .map(Teacher::getId).collect(Collectors.toSet())
                ).stream().parallel()
                .map(teacher -> StudentTeacherCourseConverter.teacherDto(teacher,departmentRepo,courseRepo));
    }
    //=============================================================
    public ResponseEntity<?> create(TeacherDto teacherDto){
        try {
            StudentTeacherCourseConverter.teacher(teacherDto
                    ,universityRepo,facultyRepo,departmentRepo,courseRepo,teacherCourseRepo,teacherRepo);
            return ResponseEntity.ok("saved teacher successfully!");
        }catch (Exception ex){
            return ResponseEntity.ok("saved teacher un-successfully!");
        }
    }
    // ========================================================================

    public ResponseEntity<?> updateByTeacherEmail(Long teacherId,String teacherEmail){
        int updated = teacherRepo.updateByTeacherEmail(teacherId,teacherEmail);
        if(updated > 0){
            return ResponseEntity.ok(
                    String.format("update teacher with id = `%s` and email = `%s` successfully!"
                            ,teacherId,teacherEmail)
            );
        }
        return ResponseEntity.ok(
                String.format("update teacher with id = `%s` and email = `%s` un-successfully!"
                        ,teacherId,teacherEmail)
        );
    }

    //============================================================================
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            transactionManager = "studentTransactionManager")
    @DeleteMapping("/teachers/delete/{id}")
    public ResponseEntity<?> deleteByTeacherId(
            @PathVariable("id") @Valid Long teacherId){
        if (isExisted(teacherId)){
            teacherRepo.deleteById(teacherId);
            return ResponseEntity.ok(String.format("deleted teacher with ID = `%s` successfully!",teacherId));
        }
        return ResponseEntity.ok(String.format("teacher with ID = `%s` NOT existed!",teacherId));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    public ResponseEntity<?> deleteByTeacherEmail(String teacherEmail){
        Set<Teacher> teachers = teacherRepo.findByTeacherEmail(teacherEmail)
                .stream().parallel()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if(teachers.size() > 0){
            teacherRepo.deleteAllById(
                    teachers.parallelStream()
                    .map(Teacher::getId).collect(Collectors.toSet()));
            return ResponseEntity.ok(String.format("deleted all teachers with email-like = `%s` successfully!",
                    teacherEmail));
        }
        return ResponseEntity.ok(String.format("teacher with email-like = `%s` NOT existed!",teacherEmail));
    }

    public ResponseEntity<?> deleteByEmailAndStatus(
            String teacherEmail, Boolean teacherStatus
    ){
        Set<Teacher> teachers = teacherRepo.findByTeacherEmailAndStatus(teacherEmail, teacherStatus)
                .stream().parallel()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if(teachers.size() > 0){
            teacherRepo.deleteAll(teachers);
            return ResponseEntity.ok(String.format("deleted all teachers with email-like = `%s` and status = `%s` " +
                            "successfully!",teacherEmail,teacherStatus));
        }
        return ResponseEntity.ok(String.format("teacher with email-like = `%s` NOT existed!",teacherEmail));
    }

//    @GetMapping("/teachers/pagination-and-sort/email-and-status")
//    @GetMapping("/teachers/pagination-and-sort/firstname-and-lastname")

    public boolean isExisted(Long id){
        Objects.requireNonNull(id,"ID is Not null!");
        return teacherRepo.existsById(id);
    }

}
