package me.quyen.repositories.student;

import me.quyen.entities.student.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTeacherRepo extends JpaRepository<StudentTeacher,Long> {
}
