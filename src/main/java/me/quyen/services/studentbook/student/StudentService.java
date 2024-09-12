package me.quyen.services.studentbook.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.*;
import me.quyen.entities.student.converter.StudentTeacherCourseConverter;
import me.quyen.entities.student.dtos.StudentDto;
import me.quyen.entities.student.dtos.StudentResponse;
import me.quyen.repositories.student.*;
import me.quyen.repositories.student.book.*;
import me.quyen.utils.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    final UniversityRepo universityRepo;
    final FacultyRepo facultyRepo;
    final DepartmentRepo departmentRepo;
    final AuthorRepo authorRepo;
    final BookRepo bookRepo;
    final BookAuthorRepo bookAuthorRepo;
    final LibraryRepo libraryRepo;
    final PublisherRepo publisherRepo;
    final BookLibraryRepo bookLibraryRepo;
    final BookPublisherRepo bookPublisherRepo;
    final CourseRepo courseRepo;
    final StudentBookRepo studentBookRepo;
    final StudentCourseRepo studentCourseRepo;
    final StudentRepo studentRepo;
    final StudentTeacherRepo studentTeacherRepo;
    final TeacherCourseRepo teacherCourseRepo;
    final TeacherRepo teacherRepo;

    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        List<StudentResponse> studentResponses = studentRepo.findAll()
                .stream().parallel()
                .map(StudentResponse::toStudentResponse).collect(Collectors.toList());
        return ResponseEntity.ok(studentResponses);
    }


    public ResponseEntity<StudentDto> findStudentById(Long entityId) {
        Optional<Student> studentOpt = studentRepo.findById(entityId);
        if (studentOpt.isPresent()){
            Student student = studentOpt.get();
            StudentDto dsc = StudentTeacherCourseConverter.studentDto(student,bookRepo,teacherRepo,departmentRepo,courseRepo);
            return ResponseEntity.ok(dsc);
        }

        return ResponseEntity.ofNullable(null);
    }


    public ResponseEntity<List<StudentDto>> findByEmailAddressLike(String emailAddress
    ){
        List<StudentDto> studentDtoList = studentRepo.findByEmailLike(emailAddress)
                .stream().parallel()
                .map(st -> StudentTeacherCourseConverter.studentDto(st, bookRepo, teacherRepo,departmentRepo,courseRepo))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }


    public ResponseEntity<List<StudentDto>> findByPersonalDataFirstNameAndPersonalDataLastName(
            String firstName, String lastName
    ){
        List<StudentDto> studentDtoList =
                studentRepo.findByPersonalData_FirstNameLikeAndPersonalData_LastNameLike(firstName, lastName)
                        .stream().parallel()
                        .map(student -> StudentTeacherCourseConverter.studentDto(student, bookRepo, teacherRepo,
                                departmentRepo,courseRepo))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }


    public ResponseEntity<List<StudentDto>> findByPersonalDataFullName(String fullName
    ){
        List<StudentDto> studentDtoList =
                studentRepo.findByPersonalData_FullNameLike(fullName)
                        .stream().parallel()
                        .map(student -> StudentTeacherCourseConverter.studentDto(student, bookRepo, teacherRepo,
                                departmentRepo,courseRepo))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }


    public ResponseEntity<List<StudentDto>> findByPersonalDataCitizenCodeLike(String citizenCode
    ){
        List<StudentDto> studentDtoList =
                studentRepo.findByPersonalData_CitizenCodeLike(citizenCode)
                        .stream().parallel()
                        .map(student -> StudentTeacherCourseConverter.studentDto(student, bookRepo, teacherRepo,
                                departmentRepo,courseRepo))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }


    public ResponseEntity<List<StudentDto>> findByDepartmentNameLike(String departmentName
    ){
        List<StudentDto> studentDtoList = studentRepo.findByDepartment_DepartmentNameLike(departmentName)
                .stream().parallel()
                .map(st -> StudentTeacherCourseConverter.studentDto(st, bookRepo, teacherRepo,departmentRepo,courseRepo))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }

    public ResponseEntity<List<StudentDto>> findByFacultyNameLike(String facultyName
    ){
        List<StudentDto> studentDtoList = studentRepo.findByStudentIds(
                        facultyRepo.findByFacultyNameContaining(facultyName)
                                .stream().parallel()
                                .map(Faculty::getDepartments).flatMap(Collection::parallelStream).distinct()
                                .map(Department::getStudents).flatMap(Collection::parallelStream).distinct()
                                .map(Student::getId).distinct().collect(Collectors.toList())
                ).stream().parallel()
                .map(s -> StudentTeacherCourseConverter.studentDto(s, bookRepo, teacherRepo,
                        departmentRepo,courseRepo)
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtoList);
    }

    public ResponseEntity<List<StudentDto>> findByUniversityNameLike(String universityName
    ){
        List<StudentDto> studentDtoList =
                studentRepo.findByStudentIds(
                                universityRepo.findByUniversityNameContaining(universityName).stream().parallel()
                                        .map(University::getFaculties).flatMap(Collection::parallelStream)
                                        .map(Faculty::getDepartments).flatMap(Collection::parallelStream).distinct()
                                        .map(Department::getStudents).flatMap(Collection::parallelStream).map(Student::getId).distinct()
                                        .collect(Collectors.toList())
                        ).stream().parallel()
                        .map(s -> StudentTeacherCourseConverter.studentDto(s, bookRepo, teacherRepo,departmentRepo,
                                courseRepo))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(studentDtoList);
    }

    public ResponseEntity<List<StudentDto>> findByTeacherFullNameLike(String teacherFullName
    ){
        Set<Long> studentIds = teacherRepo.findByPersonalData_FullNameContaining(teacherFullName)
                .stream().parallel().map(Teacher::getStudentTeachers)
                .flatMap(Collection::stream)
                .map(st -> st.getStudent().getId()).collect(Collectors.toSet());
        List<StudentDto> studentDtoList = studentRepo.findByStudentIds(studentIds).stream()
                .map(student -> StudentTeacherCourseConverter.studentDto(student, bookRepo, teacherRepo,
                        departmentRepo,courseRepo)).toList();

        return ResponseEntity.ok(studentDtoList);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public ResponseEntity<?> create(StudentDto entityDto) {
        try {
            StudentTeacherCourseConverter.student(entityDto
                    ,universityRepo, facultyRepo, departmentRepo, courseRepo,teacherCourseRepo
                    ,teacherRepo,bookRepo,authorRepo,libraryRepo,publisherRepo,bookAuthorRepo,bookLibraryRepo
                    ,bookPublisherRepo,studentBookRepo,studentTeacherRepo,studentCourseRepo,studentRepo);
            return ResponseEntity.ok("saved student successfully!");
        }catch (Exception ex){
            return ResponseEntity.ok("saved student un-successfully!");
        }

    }

    @Transactional(transactionManager = "studentTransactionManager")
    public ResponseEntity<?> update(Long entityId, String email) {
        int updated = studentRepo.updateByStudentEmail(entityId, email);
        if(updated > 0 ) return ResponseEntity.ok("Updated successfully!");
        return ResponseEntity.ok("Updated Failed!");
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public ResponseEntity<?> deleteById(Long entityId) {
        studentRepo.deleteById(entityId);
        return ResponseEntity.noContent().build();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    public ResponseEntity<?> deleteByEmailLike(String studentEmail) {
        studentRepo.deleteAllById(
                studentRepo.findByEmailLike(studentEmail)
                        .stream().parallel().map(Student::getId)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.noContent().build();

    }
    @Transactional(propagation = Propagation.REQUIRES_NEW
            ,transactionManager = "studentTransactionManager")
    public ResponseEntity<?> deleteByStudentEmailLikeAndStudentStatus(
            String studentEmail, Boolean studentStatus){
        studentRepo.deleteAll(studentRepo.findByStudentEmailLikeAndStudentStatus(studentEmail,studentStatus));
        return ResponseEntity.noContent().build();
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

    final HttpServletResponse response;
    public ResponseEntity<Map<String,Object>> findByStudentEmailLikeAndStudentStatusWithSortPagination(
            String studentEmail, Boolean studentStatus
            , int page, int size, String[] sort
    ){
        try{
            List<Sort.Order> orders = new ArrayList<>();
            if(sort[0].contains(",")){
                orders.addAll(PaginationUtil.sortOrders(sort));
            }else {
                // default sort: sort=id,asc
                orders.add(PaginationUtil.idSortedOrder(sort));
                // 2 additional sorts: sort=email,asc&sort=status,desc
                orders.add(new Sort.Order(Sort.Direction.ASC,"email"));
                orders.add(new Sort.Order(Sort.Direction.DESC,"status"));
            }
            int maxPage = (page > 100) ? 0 : page;
            int maxSize = (size > 20) ? 20 : size;
            Pageable pageable = PageRequest.of(maxPage, maxSize, Sort.by(orders));
            Page<Student> studentPage =
                    studentRepo.findByStudentEmailLikeAndStudentStatusWithSortPagination(studentEmail,studentStatus,pageable);
            List<StudentResponse> studentDtoList = studentPage.getContent().stream().parallel()
                    .map(StudentResponse::toStudentResponse)
                    .collect(Collectors.toList());
            Map<String,Object> results = new LinkedHashMap<>();
            results.put("1.current-page: ", studentPage.getNumber());
            results.put("2.current-page-size: ", studentPage.getSize());
            results.put("3.total-items: ", studentPage.getTotalElements());
            results.put("4.total-pages: ", studentPage.getTotalPages());
            results.put("5.offset: ", studentPage.getPageable().getOffset());
            results.put("6.sort-properties: ", studentPage.getSort());
            results.put("7.sort-types: ", studentPage.getSort().get().collect(Collectors.toList()));
            results.put("8.data: ", studentDtoList);

            response.setHeader("application-id",UUID.randomUUID().toString());
            response.setHeader("frame-work","spring.org");
            response.addHeader("application-name","secured-rest-api");
            return new ResponseEntity<Map<String,Object>>(results,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
