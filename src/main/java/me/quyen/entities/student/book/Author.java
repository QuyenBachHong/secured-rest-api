package me.quyen.entities.student.book;


import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.AuthorData;
import me.quyen.entities.student.PersonalData;
import me.quyen.entities.student.address.Address;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "AUTHORS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authorCode",nullable = false,length = 10)
    private String authorCode;
    @Column(name = "pseudonym",nullable = false)
    private String pseudonym;
    @Column(name = "authorEmail",nullable = false)
    private String authorEmail;
    @Column(name = "authorUrl",nullable = false)
    private String authorUrl;
    @Embedded
    private PersonalData personalData;
    @Embedded
    private Address address;
    @OneToMany(
            mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("author")
    private Set<BookAuthor> bookAuthors = new LinkedHashSet<>();

    public static Author of(){
        PersonalData personalData = PersonalData.of();
        Author me = new Author();
        String[] authorInfo = AuthorData.namesAndCodesAndEmailsAndUrls();
        me.setPseudonym(authorInfo[0]);
        me.setAuthorCode(authorInfo[1].toUpperCase());
        me.setAuthorEmail(authorInfo[2]);
        me.setAuthorUrl(authorInfo[3]);
        me.setPersonalData(personalData);
        me.setAddress(Address.of());
        return me;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;
        return id != null && Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
