package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.fakadata.GradeData;
import me.quyen.entities.student.address.Address;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "STUDENTS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private boolean status;
    private boolean isSenior;
    private String gradeLevel;
    private String studentCode;
    @Embedded
    private PersonalData personalData;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("student")
    private Set<StudentTeacher> studentTeachers = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("student")
    private Set<StudentCourse> studentCourses = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("students")
    private Department department;

    @OneToMany(
            mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("student")
    private Set<StudentBook> studentBooks = new LinkedHashSet<>();

    public static Student of(Department department){
        Objects.requireNonNull(department);
        PersonalData personalData = PersonalData.of();
        String firstName = personalData.getFirstName();
        String lastName = personalData.getLastName();
        String studentCode = CodeGenerator.code(5,5);
        Student me = new Student();
        me.setStudentCode(studentCode);
        me.setEmail(me.studentEmail(firstName.toLowerCase(),lastName.toLowerCase()));
        me.setStatus(ThreadLocalRandom.current().nextBoolean());
        me.setSenior(ThreadLocalRandom.current().nextBoolean());
        me.setGradeLevel(GradeData.gradeName());
        me.setPersonalData(personalData);
        me.setAddress(Address.of());
        me.addAssociation(department);
        return me;
    }
    public void addAssociation(Department department){
        this.department = department;
        Optional.ofNullable(department).ifPresent(it -> it.getStudents().add(this));
    }
    @PreRemove
    public void removeAssociations(){
        System.out.println("In `public void me.quyen.entities.student.Student.removeAssociations()` at "
                + (LocalDateTime.now()));
        Optional.ofNullable(this.department)
                .ifPresent(it -> it.getStudents().remove(this));
    }
    private String studentEmail(String firstName, String lastName){
        String[] mailTails
                = new String[]{
                        "@gmail.com","@outlook.com","@live.com","@hotmail.com"
                        ,"@msn.com","@yahoo.com", "@email.me", "@springmvc.org","@springboot.org"
        };
        int rInt = ThreadLocalRandom.current().nextInt(0,mailTails.length);
        return firstName.concat(".").concat(lastName).concat(mailTails[rInt]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return id != null && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
