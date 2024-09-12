package me.quyen.entities.student;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.fakadata.DepartmentData;
import me.quyen.entities.student.address.Address;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "DEPARTMENTS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="departmentName")
    private String departmentName;
    @Column(name="departmentCode")
    private String departmentCode;
    @Column(name="establishedDate")
    private LocalDate establishedDate;
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultyId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("departments")
    private Faculty faculty;

    @OneToMany(
            mappedBy = "department",targetEntity = Student.class
            ,cascade = {DETACH, PERSIST,MERGE,REFRESH}
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("department")
    private Set<Student> students = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "department",targetEntity = Teacher.class
            ,cascade = {DETACH, PERSIST,MERGE,REFRESH}
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("department")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    public static Department of(Faculty faculty){
        Objects.requireNonNull(faculty);
        Department me = new Department();
        String[] namesAndCodes = DepartmentData.namesAndCodes();
        me.setDepartmentName(namesAndCodes[0]);
        me.setDepartmentCode(namesAndCodes[1].toUpperCase());
        me.setEstablishedDate(CodeGenerator.randomEstablishedDate());
        me.setAddress(Address.of());
        me.addAssociation(faculty);
        return me;
    }
    public void addAssociation(Faculty faculty){
        this.faculty = faculty;
        Optional.ofNullable(faculty).ifPresent(it -> it.getDepartments().add(this));
    }
    @PreRemove
    public void removeAssociation(){
        this.students.forEach(it -> it.setDepartment(null));
        this.teachers.forEach(it -> it.setDepartment(null));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Department that = (Department) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
