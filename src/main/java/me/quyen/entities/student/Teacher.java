package me.quyen.entities.student;

import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.AcademicPositionData;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.fakadata.ScienceDegreePositionData;
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
@Entity @Table(name="TEACHERS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private boolean status;
    private int experiencedYear;
    private String academicDegree;
    private String scienceDegree;
    private String sciencePosition;
    @Embedded
    private PersonalData personalData;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "teacher",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("teacher")
    private Set<StudentTeacher> studentTeachers = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "teacher",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("teacher")
    private Set<TeacherCourse> teacherCourses = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("teachers")
    private Department department;

    public static Teacher of(Department department){
        Objects.requireNonNull(department);
        PersonalData personalData = PersonalData.of();
        String firstName = personalData.getFirstName();
        String lastName = personalData.getLastName();
        Teacher me = new Teacher();
        me.setEmail(me.teacherEmail(firstName.toLowerCase(),lastName.toLowerCase()));
        me.setStatus(ThreadLocalRandom.current().nextBoolean());
        me.setExperiencedYear(ThreadLocalRandom.current().nextInt(1,31));
        me.setAcademicDegree(AcademicPositionData.randomPosition());
        me.setScienceDegree(ScienceDegreePositionData.scienceDegreeName());
        me.setSciencePosition(ScienceDegreePositionData.scienceDegreeCode());
        me.setPersonalData(personalData);
        me.setAddress(Address.of());
        me.addAssociation(department);
        return me;
    }
    public void addAssociation(Department department){
        this.department = department;
        Optional.ofNullable(department).ifPresent(it -> it.getTeachers().add(this));
    }
    @PreRemove
    public void removeAssociations(){
        System.out.println("In `public void me.quyen.entities.student.Teacher.removeAssociations()` at "
                + (LocalDateTime.now()));
        Optional.ofNullable(this.department).ifPresent(it ->
                it.getTeachers().remove(this));
    }
    private String teacherEmail(String firstName, String lastName){
        String[] mailTails
                = new String[]{"@gmail.com","@outlook.com","@live.com","@hotmail.com"
                ,"@msn.com","@yahoo.com", "@email.me"};
        int rInt = ThreadLocalRandom.current().nextInt(0,mailTails.length);
        return firstName.concat(".").concat(lastName).concat(mailTails[rInt]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return id != null && Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
