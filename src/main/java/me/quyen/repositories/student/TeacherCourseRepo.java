package me.quyen.repositories.student;

import me.quyen.entities.student.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherCourseRepo extends JpaRepository<TeacherCourse,Long> {
}
