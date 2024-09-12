package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Optional;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="TEACHERS_COURSES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class TeacherCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("teacherCourses")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("teacherCourses")
    private Course course;

    public static TeacherCourse of(Teacher teacher, Course course){
        Objects.requireNonNull(teacher);
        Objects.requireNonNull(course);
        TeacherCourse me = new TeacherCourse();
        me.addAssociation(teacher,course);
        return me;
    }
    public void addAssociation(Teacher teacher, Course course){
        this.teacher = teacher;
        this.course = course;
        Optional.ofNullable(teacher).ifPresent(it -> it.getTeacherCourses().add(this));
        Optional.ofNullable(course).ifPresent(it -> it.getTeacherCourses().add(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherCourse that = (TeacherCourse) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
