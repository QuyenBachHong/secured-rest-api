package me.quyen.controllers.studentbook.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.dtos.TeacherDto;
import me.quyen.entities.student.dtos.TeacherResponse;
import me.quyen.services.studentbook.teacher.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {
    final TeacherService teacherService;
    @GetMapping("/teachers")
    public ResponseEntity<?> findAllTeachers() {
        List<TeacherResponse> teacherResponses = teacherService.findAllTeachers().collect(Collectors.toList());
        if(teacherResponses.size() > 0){
            return ResponseEntity.ok(teacherResponses);
        }
        return ResponseEntity.ok("No any Teacher entity!");
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> findByTeacherId(@PathVariable("id") @Valid Long teacherId) {
        Optional<TeacherDto> findByTeacherId = teacherService.findByTeacherId(teacherId);
        if (findByTeacherId.isPresent()){
            return ResponseEntity.ok(findByTeacherId.get());
        }
        return ResponseEntity.ok(String.format("teacher with id = `%s` NOT existed!",teacherId));
    }

    @GetMapping("/teachers/email-address/{email}")
    public ResponseEntity<?> findByTeacherEmail(@PathVariable("email") @Valid String teacherEmail) {
        List<TeacherDto> teacherDtoList = teacherService.findByTeacherEmail(teacherEmail).collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with email-like `%s` NOT existed!",teacherEmail));
    }

    @GetMapping("/teachers/status/{status}")
    public ResponseEntity<?> findByTeacherStatus(
            @PathVariable("status") @Valid Boolean teacherStatus) {
        List<TeacherDto> teacherDtoList = teacherService.findByTeacherStatus(teacherStatus)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with status = `%s` NOT existed!",teacherStatus));
    }

    @GetMapping("/teachers/email-address/{email}/status/{status}")
    public ResponseEntity<?> findByTeacherEmailAndStatus(
            @PathVariable("email") @Valid String teacherEmail
            , @PathVariable("status") @Valid Boolean teacherStatus) {
        List<TeacherDto> teacherDtoList = teacherService.findByTeacherEmailAndStatus(teacherEmail,teacherStatus)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with email-like `%s` and status = `%s` NOT existed!",
                teacherEmail,teacherStatus));
    }

    @GetMapping("/teachers/personal-data/first-name/{firstname}/last-name/{lastname}")
    public ResponseEntity<?> findByTeacherFirstNameAndLastName(
            @PathVariable("firstname") @Valid String firstName
            , @PathVariable("lastname") @Valid String lastName) {
        List<TeacherDto> teacherDtoList = teacherService.findByTeacherFirstNameAndLastName(firstName,lastName)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with firstname-like `%s` and lastname-like = `%s` NOT existed!",
                firstName,lastName));
    }

    @GetMapping("/teachers/personal-data/full-name/{fullname}")
    public ResponseEntity<?> findByPersonalData_FullNameContaining(
            @PathVariable("fullname") @Valid String teacherFullName) {
        List<TeacherDto> teacherDtoList = teacherService.findByPersonalData_FullNameContaining(teacherFullName)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with fullname-like `%s` NOT existed!",
                teacherFullName));
    }

    @GetMapping("/teachers/personal-data/citizen-id/{citizentId}")
    public ResponseEntity<?> findByCitizenCode(@PathVariable("citizentId") @Valid String citizenCode) {
        List<TeacherDto> teacherDtoList = teacherService.findByCitizenCode(citizenCode)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with citizen-code-like `%s`NOT existed!",
                citizenCode));
    }

    @GetMapping("/teachers/department/department-name/{departmentName}")
    public ResponseEntity<?> findByDepartmentName(
            @PathVariable("departmentName") @Valid String departmentName) {
        List<TeacherDto> teacherDtoList = teacherService.findByDepartmentName(departmentName)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with department-name-like `%s`NOT existed!",
                departmentName));
    }

    @GetMapping("/teachers/faculty/faculty-name/{facultyName}")
    public ResponseEntity<?> findByFacultyName(
            @PathVariable("facultyName") @Valid String facultyName) {
        List<TeacherDto> teacherDtoList = teacherService.findByFacultyName(facultyName)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with faculty-name-like `%s`NOT existed!",
                facultyName));
    }

    @GetMapping("/teachers/university/university-name/{universityName}")
    public ResponseEntity<?> findByUniversityName(
            @PathVariable("universityName") @Valid String universityName) {
        List<TeacherDto> teacherDtoList = teacherService.findByUniversityName(universityName)
                .collect(Collectors.toList());
        if (teacherDtoList.size() > 0){
            return ResponseEntity.ok(teacherDtoList);
        }
        return ResponseEntity.ok(String.format("teacher with university-name-like `%s`NOT existed!",
                universityName));
    }
    //=========================================================
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            transactionManager = "studentTransactionManager")
    @PostMapping("/teachers/create")
    public ResponseEntity<?> create(@RequestBody @Valid TeacherDto teacherDto){
        return teacherService.create(teacherDto);
    }
    // ========================================================================
    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    @PutMapping("/teachers/update-by-email/id/{id}/email/{email}")
    public ResponseEntity<?> updateByTeacherEmail(
            @PathVariable("id") @Valid Long teacherId
            ,@PathVariable("email") @Valid String teacherEmail){
        return teacherService.updateByTeacherEmail(teacherId,teacherEmail);
    }

    //============================================================================
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            transactionManager = "studentTransactionManager")
    @DeleteMapping("/teachers/delete/{id}")
    public ResponseEntity<?> deleteByTeacherId(
            @PathVariable("id") @Valid Long teacherId){
        return teacherService.deleteByTeacherId(teacherId);
    }

    @DeleteMapping("/teachers/delete-by-email/{email}")
    public ResponseEntity<?> deleteByTeacherEmail(@PathVariable("email") @Valid String teacherEmail){
        return teacherService.deleteByTeacherEmail(teacherEmail);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    @DeleteMapping("/teachers/delete/email-and-status")
    public ResponseEntity<?> deleteByEmailAndStatus(
            @RequestBody @Valid EmailAndStatus emailAndStatus
    ){
        if((emailAndStatus.email() == null) && (emailAndStatus.status() == null)){
            return teacherService.deleteByEmailAndStatus("@gmail.com",false);
        }else if((emailAndStatus.email() != null) && (emailAndStatus.status() == null)) {
            return teacherService.deleteByEmailAndStatus(emailAndStatus.email(), false);
        }else if((emailAndStatus.email() == null) && (emailAndStatus.status() != null)) {
            return teacherService.deleteByEmailAndStatus("@gmail.com", emailAndStatus.status() );
        }
        return teacherService.deleteByEmailAndStatus(emailAndStatus.email(),emailAndStatus.status());
    }

//    @GetMapping("/teachers/pagination-and-sort/email-and-status")
//    @GetMapping("/teachers/pagination-and-sort/firstname-and-lastname")

    public boolean isExisted(Long id){
        return teacherService.isExisted(id);
    }
    public record EmailAndStatus(String email, Boolean status){}
}
