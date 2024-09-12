package me.quyen.controllers.studentbook.student;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.dtos.StudentDto;
import me.quyen.entities.student.dtos.StudentResponse;
import me.quyen.services.studentbook.student.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
    final StudentService studentService;
    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable("id") Long entityId) {
        return studentService.findStudentById(entityId);
    }

    @GetMapping("/students/email-address/{email}")
    public ResponseEntity<List<StudentDto>> findByEmailAddressLike(
            @PathVariable("email") String emailAddress
    ){
        return studentService.findByEmailAddressLike(emailAddress);
    }

    @GetMapping("/students/personal-data/first-name/{firstname}/last-name/{lastname}")
    public ResponseEntity<List<StudentDto>> findByPersonalDataFirstNameAndPersonalDataLastName(
            @PathVariable("firstname") String firstName, @PathVariable("lastname") String lastName
    ){
        return studentService.findByPersonalDataFirstNameAndPersonalDataLastName(firstName,lastName);
    }

    @GetMapping("/students/personal-data/full-name/{fullname}")
    public ResponseEntity<List<StudentDto>> findByPersonalDataFullName(
            @PathVariable("fullname") String fullName
    ){
        return studentService.findByPersonalDataFullName(fullName);
    }

    @GetMapping("/students/personal-data/citizen-id/{citizentId}")
    public ResponseEntity<List<StudentDto>> findByPersonalDataCitizenCodeLike(
            @PathVariable("citizentId") String citizenCode
    ){
        return studentService.findByPersonalDataCitizenCodeLike(citizenCode);
    }

    @GetMapping("/students/department/department-name/{departmentName}")
    public ResponseEntity<List<StudentDto>> findByDepartmentNameLike(
            @PathVariable("departmentName") @Valid String departmentName
    ){
        return studentService.findByDepartmentNameLike(departmentName);
    }
    @GetMapping("/students/faculty/faculty-name/{facultyName}")
    public ResponseEntity<List<StudentDto>> findByFacultyNameLike(
            @PathVariable("facultyName") @Valid String facultyName
    ){
        return studentService.findByFacultyNameLike(facultyName);
    }
    @GetMapping("/students/university/university-name/{universityName}")
    public ResponseEntity<List<StudentDto>> findByUniversityNameLike(
            @PathVariable("universityName") @Valid String universityName
    ){
        return studentService.findByUniversityNameLike(universityName);
    }
    @GetMapping("/students/teacher/teacher-name/{teacherName}")
    public ResponseEntity<List<StudentDto>> findByTeacherFullNameLike(
            @PathVariable("teacherName") @Valid String teacherFullName
    ){
        return studentService.findByTeacherFullNameLike(teacherFullName);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    @PostMapping("/students/create")
    public ResponseEntity<?> create(@RequestBody StudentDto entityDto) {
        return studentService.create(entityDto);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    @PutMapping("/students/update-by-email/id/{id}/email/{email}")
    public ResponseEntity<?> update(@PathVariable("id") Long entityId
            , @PathVariable("email") String email) {
        return studentService.update(entityId,email);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long entityId) {
            return studentService.deleteById(entityId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    @DeleteMapping("/students/delete-by-email/{email}")
    public ResponseEntity<?> deleteByEmailLike(@PathVariable("email") String studentEmail) {
        return studentService.deleteByEmailLike(studentEmail);

    }
    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    @DeleteMapping("/students/delete/email/{email}/status/{status}")
    public ResponseEntity<?> deleteByStudentEmailLikeAndStudentStatus(
            @PathVariable("email") String studentEmail,
            @PathVariable("status") Boolean studentStatus){
        return studentService.deleteByStudentEmailLikeAndStudentStatus(studentEmail,studentStatus);
    }


    /**
     * `page`: zero-based page index, must NOT be negative.
     * `size`: number of items in a page to be returned, must be greater than 0.
     * String[] sort = {"field-to-sort,direction}" ex: {"email,asc"} or {"status,desc"}
     * /api/students/pagination-and-sort?page={pageNum}&size={pageSize}&sort=email,desc&sort=status,asc
     * ?sort=column1,direction1: sorting single column
     * String[] sort is an array with 2 elements: [“column1”, “direction1”]
     * ?sort=column1,direction1&sort=column2,direction2: sorting multiple columns
     * String[] sort is also an array with 2 elements: [“column1, direction1”, “column2, direction2”]
     *
     * need to convert "asc"/"desc" into Sort.Direction.ASC/Sort.Direction.DES for working with Sort.Order class
     * Sort.Order#Order(Sort.Direction direction, String property)
     * where: enum Sort.Direction{ASC,DESC}
     * && enum Sort.NullHandling{NATIVE,NULLS_FIRST,NULLS_LAST}
     *
     */
    @GetMapping("/students/pagination-and-sort/email-and-status")
    public ResponseEntity<Map<String,Object>> findByStudentEmailLikeAndStudentStatusWithSortPagination(
            @RequestParam(defaultValue = "@gmail.com",name = "email") String studentEmail
            , @RequestParam(defaultValue = "true",name = "status") Boolean studentStatus
            , @RequestParam(defaultValue = "0",name = "page-number") int page
            , @RequestParam(defaultValue = "5",name = "page-size") int size
            , @RequestParam(defaultValue = "id,asc") String[] sort
            ) {
        return studentService.findByStudentEmailLikeAndStudentStatusWithSortPagination(
                studentEmail, studentStatus, page, size, sort
        );
    }

}
