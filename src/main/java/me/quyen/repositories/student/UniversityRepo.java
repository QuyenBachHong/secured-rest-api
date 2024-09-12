package me.quyen.repositories.student;

import me.quyen.entities.student.University;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface UniversityRepo extends JpaRepository<University,Long> {

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u")
    Streamable<University> findUniversities();

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.id in :ids")
    Streamable<University> findByUniversityIds(@Param("ids") Iterable<Long> universityIds);

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.id = :id")
    Optional<University> findByUniversityId(@Param("id") Long universityId);

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.universityName like %:NAME%")
    Streamable<University> findByUniversityNameContaining(
            @Param("NAME") String universityName
    );

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.universityCode like %:CODE%")
    Streamable<University> findByUniversityCodeContaining(
            @Param("CODE") String universityCode
    );

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.emailAddress like %:EMAIL%")
    Streamable<University> findByUniversityEmailContaining(
            @Param("EMAIL") String universityEmail
    );

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.address.countryName like %:NAME%")
    Streamable<University> findByUniversityCountryNameContaining(
            @Param("NAME") String countryName
    );

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.address.countryName like %:NAME% and u.address.state like %:STATE%")
    Streamable<University> findByCountryNameAndStateNameContaining(
            @Param("NAME") String countryName,@Param("STATE") String state
    );

    @EntityGraph(attributePaths = {
            "address","faculties.departments.students","faculties.departments.teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from University u where u.address.state like %:STATE% and u.address.zipCode like %:ZIP_CODE%")
    Streamable<University> findByStateNameAndZipCodeContaining(
            @Param("STATE") String state,@Param("ZIP_CODE") String zipCode
    );
}
