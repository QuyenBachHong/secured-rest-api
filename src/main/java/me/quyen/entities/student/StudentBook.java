package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.student.book.Book;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "STUDENTS_BOOKS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class StudentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("studentBooks")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("studentBooks")
    private Book book;

    public static StudentBook of(Student student, Book book){
        Objects.requireNonNull(student);
        Objects.requireNonNull(book);
        StudentBook me = new StudentBook();
        me.addAssociation(student,book);
        return me;
    }
    public void addAssociation(Student student, Book book){
        this.student = student;
        this.book = book;
        student.getStudentBooks().add(this);
        book.getStudentBooks().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentBook that = (StudentBook) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
