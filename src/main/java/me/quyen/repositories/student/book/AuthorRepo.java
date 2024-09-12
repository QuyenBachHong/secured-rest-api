package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface AuthorRepo extends JpaRepository<Author,Long> {


    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Author> findAll();

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Author> findAllById(Iterable<Long> longs);

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Author> findById(Long aLong);

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Author a where a.personalData.fullName like %:fname%")
    Streamable<Author> findByAuthorPersonalDataFullName(@Param("fname") String fullName);

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Author a where a.personalData.firstName like %:firstName% and a.personalData.lastName like " +
            "%:lastName%")
    Streamable<Author> findByAuthorPersonalDataFirstNameAndLastName(
        @Param("firstName") String firstName, @Param("lastName") String lastName);

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Author a where a.personalData.citizenCode like %:code%")
    Streamable<Author> findByAuthorCitizenCode(@Param("code") String citizenCode);

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Author a where a.authorEmail like %:email%")
    Streamable<Author> findByAuthorEmail(@Param("email") String authorEmail);

    @EntityGraph(attributePaths = {"bookAuthors.book", "personalData","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select a from Author a where a.pseudonym like %:pseudonym%")
    Streamable<Author> findByAuthorPseudonym(@Param("pseudonym") String pseudonym);


}
