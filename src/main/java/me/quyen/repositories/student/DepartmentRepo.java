package me.quyen.repositories.student;

import me.quyen.entities.student.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DepartmentRepo extends JpaRepository<Department,Long> {

    @EntityGraph(attributePaths = {"address","faculty.university", "students", "teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Department> findById(Long id);

    @EntityGraph(attributePaths = {"address","faculty.university", "students", "teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Department> findAll();

    @EntityGraph(attributePaths = {"address","faculty.university", "students", "teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Department> findAllById(Iterable<Long> longs);

    @EntityGraph(attributePaths = {"address","faculty.university", "students", "teachers"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select d from Department d where d.departmentName like %:NAME%")
    Streamable<Department> findByDepartmentName(@Param("NAME") String departmentName);

}
