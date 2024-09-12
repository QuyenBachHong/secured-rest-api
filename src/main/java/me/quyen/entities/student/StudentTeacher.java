package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="STUDENTS_TEACHERS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class StudentTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("studentTeachers")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("studentTeachers")
    private Teacher teacher;

    public static  StudentTeacher of(Student student,Teacher teacher){
        Objects.requireNonNull(student);
        Objects.requireNonNull(teacher);
        StudentTeacher me = new StudentTeacher();
        me.addAssociation(student,teacher);
        return me;
    }
    public void addAssociation(Student student,Teacher teacher){
        this.student = student;
        this.teacher = teacher;
        student.getStudentTeachers().add(this);
        teacher.getStudentTeachers().add(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentTeacher that = (StudentTeacher) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
