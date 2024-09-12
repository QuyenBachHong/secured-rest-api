package me.quyen.repositories.student;

import me.quyen.entities.student.Faculty;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FacultyRepo extends JpaRepository<Faculty,Long> {


    @EntityGraph(attributePaths = {
            "address","departments.students", "departments.teachers","faculty.university"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Faculty> findAll();

    @EntityGraph(attributePaths = {
            "address","departments.students", "departments.teachers","faculty.university"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Faculty> findAllById(Iterable<Long> longs);

    @EntityGraph(attributePaths = {
            "address","departments.students", "departments.teachers","faculty.university"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Faculty> findById(Long aLong);


    @EntityGraph(attributePaths = {
            "address","departments.students", "departments.teachers","university"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select f from Faculty f where f.facultyName like %:NAME%")
    Streamable<Faculty> findByFacultyNameContaining(
            @Param("NAME") String facultyName
    );

    @EntityGraph(attributePaths = {
            "address","departments.students", "departments.teachers","faculty.university"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select f from Faculty f where f.facultyCode like %:CODE%")
    Streamable<Faculty> findByFacultyCodeContaining(
            @Param("CODE") String facultyCode
    );
}
