package me.quyen.entities.student;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.fakadata.FacultyData;
import me.quyen.entities.student.address.Address;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "FACULTIES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Faculty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="facultyName")
    private String facultyName;
    @Column(name="facultyCode")
    private String facultyCode;
    @Column(name="establishedDate")
    private LocalDate establishedDate;
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universityId",referencedColumnName = "id")
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("faculties")
    private University university;

    @OneToMany(
            mappedBy = "faculty",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("faculty")
    private Set<Department> departments = new LinkedHashSet<>();

    public static Faculty of(University university){
        Objects.requireNonNull(university);
        Faculty me = new Faculty();
        String[] namesAndCodes = FacultyData.namesAndCodesOf();
        me.setFacultyName(namesAndCodes[0]);
        me.setFacultyCode(namesAndCodes[1].toUpperCase());
        me.setEstablishedDate(CodeGenerator.randomEstablishedDate());
        me.setAddress(Address.of());
        me.addAssociation(university);
        return me;
    }
    public void addAssociation(University university){
        this.university = university;
        Optional.ofNullable(university).ifPresent(it -> it.getFaculties().add(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Faculty faculty = (Faculty) o;
        return id != null && Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
