package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepo extends JpaRepository<BookAuthor,Long> {
}
