package me.quyen.repositories.student;

import me.quyen.entities.student.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t")
    Streamable<Teacher> findAllTeachers();

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.id = :ID")
    Optional<Teacher> findByTeacherId(@Param("ID") Long teacherId);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.id in :IDS")
    Streamable<Teacher> findByTeacherIds(@Param("IDS") Iterable<Long> teacherIds);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.personalData.fullName like %:FULL_NAME%")
    Streamable<Teacher> findByPersonalData_FullNameContaining(
            @Param("FULL_NAME") String teacherFullName);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.personalData.firstName like %:FNAME% and t.personalData.lastName like " +
            "%:LNAME%")
    Streamable<Teacher> findByTeacherFirstNameAndLastName(
            @Param("FNAME") String firstName,@Param("LNAME") String lastName);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.email like %:EMAIL%")
    Streamable<Teacher> findByTeacherEmail(@Param("EMAIL") String teacherEmail);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.status = :STATUS")
    Streamable<Teacher> findByTeacherStatus(@Param("STATUS") Boolean teacherStatus);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.email like %:EMAIL% and t.status = :STATUS")
    Streamable<Teacher> findByTeacherEmailAndStatus(
            @Param("EMAIL") String teacherEmail, @Param("STATUS") Boolean teacherStatus);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.personalData.citizenCode like %:CODE%")
    Streamable<Teacher> findByCitizenCode(@Param("CODE") String citizenCode);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.experiencedYear = :YEAR")
    Streamable<Teacher> findByExperiencedYear(@Param("YEAR") int experiencedYear);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.experiencedYear >= :MIN and t.experiencedYear <= :MAX")
    Streamable<Teacher> findByExperiencedYearBetween(
            @Param("MIN") int minExperiencedYear
            ,@Param("MAX") int maxExperiencedYear);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.academicDegree like upper(concat('%',:DEGREE,'%'))")
    Streamable<Teacher> findByAcademicDegree(@Param("DEGREE") String academicDegree);

    @EntityGraph(attributePaths = {
            "department.faculty.university", "studentTeachers.student"
            , "teacherCourses.course", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select t from Teacher t where t.scienceDegree like %:DEGREE%")
    Streamable<Teacher> findByScienceDegree(@Param("DEGREE") String scienceDegree);
    //========================================================================
    @Modifying
    @Query("update Teacher t set t.email = :EMAIL where t.id = :ID")
    int updateByTeacherEmail(@Param("ID") Long teacherId,@Param("EMAIL") String teacherEmail);

    @Modifying
    @Override
    void deleteById(Long teacherId);

}
