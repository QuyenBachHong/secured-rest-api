package me.quyen.entities.student.book;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="BOOKS_LIBRARIES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class BookLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("bookLibraries")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libraryId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("bookLibraries")
    private Library library;

    public static BookLibrary of(Book book, Library library){
        Objects.requireNonNull(library);
        Objects.requireNonNull(book);
        BookLibrary me = new BookLibrary();
        me.addAssociation(book,library);
        return me;
    }
    public void addAssociation(Book book, Library library){
        this.library = library;
        this.book = book;
        book.getBookLibraries().add(this);
        library.getBookLibraries().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookLibrary that = (BookLibrary) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
