package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CourseData;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "COURSES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="courseName",nullable = false)
    private String courseName;
    @Column(name="courseCode",nullable = false)
    private String courseCode;
    @Column(name="publishedDate",nullable = false)
    private LocalDate publishedDate;

    @OneToMany(
            mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("course")
    private Set<TeacherCourse> teacherCourses = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("course")
    private Set<StudentCourse> studentCourses = new LinkedHashSet<>();

    public static Course of(){
        Course me = new Course();
        String[] namesAndCodes = CourseData.namesAndCodes();
        me.setCourseCode(namesAndCodes[1].toUpperCase());
        me.setCourseName(namesAndCodes[0]);
        int year = ThreadLocalRandom.current().nextInt(2000,2024);
        int month = ThreadLocalRandom.current().nextInt(1,13);
        int dayOfMonth = ThreadLocalRandom.current().nextInt(1,28);
        me.setPublishedDate(LocalDate.of(year,month,dayOfMonth));
        return me;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
