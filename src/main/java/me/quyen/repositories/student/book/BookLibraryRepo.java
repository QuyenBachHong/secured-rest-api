package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.BookLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLibraryRepo extends JpaRepository<BookLibrary,Long> {
}
