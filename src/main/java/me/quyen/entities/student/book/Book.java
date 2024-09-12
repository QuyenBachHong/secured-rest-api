package me.quyen.entities.student.book;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.student.StudentBook;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookIsbn",length = 20)
    private String bookIsbn;
    @Column(name = "bookTitle",nullable = false)
    private String bookTitle;
    @Column(name = "bookGenre",nullable = false)
    private String bookGenre;
    @Column(name = "bookPrice",nullable = false)
    private BigDecimal bookPrice;

    @OneToMany(
            mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("book")
    private Set<BookAuthor> bookAuthors = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("book")
    private Set<StudentBook> studentBooks = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("book")
    private Set<BookLibrary> bookLibraries = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true
    )
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("book")
    @ToString.Exclude
    private Set<BookPublisher> bookPublishers = new LinkedHashSet<>();

    public static Book of(){
        Faker f = Faker.instance();
        Book me = new Book();
        me.setBookIsbn(f.code().isbn13(true));
        me.setBookTitle(f.book().title());
        me.setBookPrice(BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(100.0,2000.0))
        );
        me.setBookGenre(f.book().genre());
        return me;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
