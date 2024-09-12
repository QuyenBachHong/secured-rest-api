package me.quyen.entities.student.book;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.fakadata.LibraryData;
import me.quyen.entities.student.address.Address;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LIBRARIES")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="libraryCode",nullable = false,length = 8)
    private String libraryCode;
    @Column(name="libraryName",nullable = false)
    private String libraryName;
    @Column(name="libraryUrl",nullable = false)
    private String libraryUrl;
    @Column(name="libraryEmail",nullable = false)
    private String libraryEmail;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "library",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("library")
    private Set<BookLibrary> bookLibraries = new LinkedHashSet<>();

    public static Library of(){
        Library me = new Library();
        String[] nameUrlEmailCode = LibraryData.nameUrlEmailCode();
        me.setLibraryCode(nameUrlEmailCode[3].toUpperCase());
        me.setLibraryName(nameUrlEmailCode[0]);
        me.setLibraryUrl(nameUrlEmailCode[1]);
        me.setLibraryEmail(nameUrlEmailCode[2]);
        me.setAddress(Address.of());
        return me;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Library library = (Library) o;
        return id != null && Objects.equals(id, library.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
