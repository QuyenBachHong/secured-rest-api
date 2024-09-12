package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPublisherRepo extends JpaRepository<BookPublisher,Long> {
}
