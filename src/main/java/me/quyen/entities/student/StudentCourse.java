package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="STUDENTS_COURSES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("studentCourses")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("studentCourses")
    private Course course;

    public static StudentCourse of(Student student, Course course){
        Objects.requireNonNull(student);
        Objects.requireNonNull(course);
        StudentCourse me = new StudentCourse();
        me.addAssociation(student,course);
        return me;
    }
    public void addAssociation(Student student, Course course){
        this.student = student;
        this.course = course;
        student.getStudentCourses().add(this);
        course.getStudentCourses().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentCourse that = (StudentCourse) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
