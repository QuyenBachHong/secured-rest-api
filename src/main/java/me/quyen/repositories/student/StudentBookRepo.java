package me.quyen.repositories.student;

import me.quyen.entities.student.StudentBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBookRepo extends JpaRepository<StudentBook,Long> {
}
