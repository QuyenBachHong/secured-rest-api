package me.quyen.repositories.student;


import me.quyen.entities.student.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Student> findAll();

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university"
            ,"studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course"
            ,"studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.id = :ID")
    Optional<Student> findById(@Param("ID") Long id);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.id in :IDS")
    Streamable<Student> findByStudentIds(@Param("IDS") Iterable<Long> ids);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.email like %:studentEmail%")
    Streamable<Student> findByStudentEmailLike(@Param("studentEmail") String email);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.email like %:studentEmail% and s.status  = :studentStatus")
    Streamable<Student> findByStudentEmailLikeAndStudentStatus(
            @Param("studentEmail") String email, @Param("studentStatus") Boolean status);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.email like %:studentEmail%")
    Streamable<Student> findByEmailLike(@Param("studentEmail") String email);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.personalData.firstName like %:firstname% and s.personalData.lastName like" +
            " %:lastname%")
    Streamable<Student> findByPersonalData_FirstNameLikeAndPersonalData_LastNameLike(
            @Param("firstname") String firstName, @Param("lastname") String lastName
    );

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.personalData.fullName like %:fullname%")
    Streamable<Student> findByPersonalData_FullNameLike(
            @Param("fullname") String fullName
    );

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.personalData.citizenCode like %:citizenId%")
    Streamable<Student> findByPersonalData_CitizenCodeLike(
            @Param("citizenId") String citizenCode
    );

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.department.departmentName like %:depName%")
    Streamable<Student> findByDepartment_DepartmentNameLike(
            @Param("depName") String departmentName
    );

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.department.departmentName like %:courseName%")
    Streamable<Student> findByCourseNameLike(
            @Param("courseName") String courseName
    );

    // =====================================================================================================
    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s")
    Page<Student> findAllWithDefaultPagination(Pageable pageable);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.email like %:email%")
    <S extends Student> Page<S> findByStudentEmailLikeWithSortedPagination(@Param("email") String studentEmail,
                                                                     Pageable pageable);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.status  = :studentStatus")
    <S extends Student> Page<S> findByStudentStatusWithSortedPagination(@Param("studentStatus") Boolean status, Pageable pageable);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.email like %:studentEmail% and s.status  = :studentStatus")
    <S extends Student> Page<S> findByStudentEmailLikeAndStudentStatusWithSortPagination(
            @Param("studentEmail") String email, @Param("studentStatus") Boolean status, Pageable pageable);

    @EntityGraph(attributePaths = {
            "personalData","address","department.faculty.university","studentCourses.course","studentBooks.book"
            ,"studentTeachers.teacher.teacherCourses.course","studentTeachers.teacher.department.faculty.university"
    }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select s from Student s where s.personalData.firstName like %:FNAME% and s.personalData.lastName " +
            "like %:LNAME%")
    <S extends Student> Page<S> findByPersonalDataFirstNameAndPersonalDataLastNameWithSortPagination(
            @Param("FNAME") String email, @Param("LNAME") String lastName, Pageable pageable);
    //==================================================================================================
    @Modifying
    @Query("update Student s set s.email = :EMAIL where s.id = :studentId")
    int updateByStudentEmail(@Param("studentId") Long id,@Param("EMAIL") String email);

    @Modifying
    void deleteById(Long id);
}
