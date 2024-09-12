package me.quyen.entities.student;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.student.address.Address;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "UNIVERSITIES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class University {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="universityName",length = 200)
    private String universityName;
    @Column(name="universityCode",length = 20)
    private String universityCode;
    @Column(name="universityPrefix",length = 20)
    private String universityPrefix;
    @Column(name="universitySuffix",length = 20)
    private String universitySuffix;
    @Column(name="emailAddress",length = 200)
    private String emailAddress;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "university",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("university")
    private Set<Faculty> faculties = new LinkedHashSet<>();

    public static University of(){
        Faker f = Faker.instance();
        University me = new University();
        String uniName = f.university().name();
        String[] emailTails = new String[]{"@outlook.com","@live.com", "@hotmail.com"
                , "@msn.com","@yahoo.com","@gmail.com"};
        me.setUniversityName(uniName);
        me.setUniversityCode(CodeGenerator.code(5,5).toUpperCase());
        me.setUniversityPrefix(f.university().prefix());
        me.setUniversitySuffix(f.university().suffix());
        me.setEmailAddress(
                uniName.replace(" ","").toLowerCase()
                        + emailTails[ThreadLocalRandom.current().nextInt(0,emailTails.length)]
        );
        me.setAddress(Address.of());
        return me;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        University that = (University) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
