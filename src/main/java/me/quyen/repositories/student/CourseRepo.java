package me.quyen.repositories.student;

import me.quyen.entities.student.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,Long> {


    @EntityGraph(attributePaths = {
            "teacherCourses.teacher", "studentCourses.student"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select c from Course c where c.courseName like %:courseName%")
    Streamable<Course> findByCourseNameContaining(
            @Param("courseName") String courseName);

    @EntityGraph(attributePaths = {
            "teacherCourses.teacher", "studentCourses.student"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select c from Course c where c.courseCode like %:courseCode%")
    Streamable<Course> findByCourseCodeContaining(
            @Param("courseCode") String courseCode);

    @EntityGraph(attributePaths = {
            "teacherCourses.teacher", "studentCourses.student"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select c from Course c")
    List<Course> findAll();

    @EntityGraph(attributePaths = {
            "teacherCourses.teacher", "studentCourses.student"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select c from Course c where c.id in :IDS")
    List<Course> findAllById(@Param("IDS") Iterable<Long> courseIds);

    @EntityGraph(attributePaths = {
            "teacherCourses.teacher", "studentCourses.student"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select c from Course c where c.id = :ID")
    Optional<Course> findById(@Param("ID") Long courseId);
}
