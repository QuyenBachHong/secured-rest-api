package me.quyen.repositories.student;

import me.quyen.entities.student.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepo extends JpaRepository<StudentCourse,Long> {
}
