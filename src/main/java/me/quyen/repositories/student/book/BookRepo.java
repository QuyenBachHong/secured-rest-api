package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
    @EntityGraph(attributePaths = {
            "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Book> findAll();

    @EntityGraph(attributePaths = {
            "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Book> findById(Long aLong);


    @EntityGraph(attributePaths = {
            "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Book> findAllById(Iterable<Long> longs);

//    @GetMapping("/books/book-isbn/{isbn}")
    @EntityGraph(attributePaths = {
            "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from Book b where b.bookIsbn like %:isbn%")
    Streamable<Book> findByBookIsbn(@Param("isbn") String bookIsbn);
//    @GetMapping("/books/book-title/{title}")
    @EntityGraph(attributePaths = {
                    "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from Book b where b.bookTitle like %:title%")
    Streamable<Book> findByBookTitle(@Param("title") String bookTitle);
//    @GetMapping("/books/book-genre/{genre}")
    @EntityGraph(attributePaths = {
            "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from Book b where b.bookGenre like %:genre%")
    Streamable<Book> findByBookGenre(@Param("genre") String bookGenre);
//    @GetMapping("/books/book-price/min-price/{min}/max-price/{max})
    @EntityGraph(attributePaths = {
            "bookAuthors.author","bookLibraries.library","bookPublishers.publisher"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from Book b where b.bookPrice >= :min and b.bookPrice <= :max")
    Streamable<Book> findByBookPriceInRange(@Param("min") BigDecimal minPrice
            ,@Param("max") BigDecimal maxPrice);

//    @PutMapping("/books/update-by-isbn/book-id/{id}/book-isbn/{isbn}")
    @Modifying
    @Query("update Book b set b.bookIsbn = :isbn where b.id = :id")
    int updateByBookIsbn(@Param("id") Long bookId, @Param("isbn") String bookIsbn);

//    @PutMapping("/books/update-by-price/book-id/{id}/book-price/{price}")
    @Modifying
    @Query("update Book b set b.bookPrice = :price where b.id = :id")
    int updateByBookPrice(@Param("id") Long bookId, @Param("price") BigDecimal bookPrice);

    @Modifying
    void deleteById(Long bookId);


}
