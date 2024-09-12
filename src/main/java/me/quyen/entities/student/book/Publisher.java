package me.quyen.entities.student.book;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.fakadata.PublisherData;
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
@Table(name = "PUBLISHERS")
@com.fasterxml.jackson.annotation.JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
        ,property = "id"
)
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name="publisherCode",nullable = false,length = 8)
    private String publisherCode;
    @Column(name="publisherName",nullable = false)
    private String publisherName;
    @Column(name="publisherUrl",nullable = false)
    private String publisherUrl;
    @Column(name="publisherEmail",nullable = false)
    private String publisherEmail;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "publisher",cascade = CascadeType.ALL,orphanRemoval = true
    )
    @ToString.Exclude
//    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("publisher")
    private Set<BookPublisher> bookPublishers = new LinkedHashSet<>();

    public static Publisher of(){
        Publisher me = new Publisher();
        String[] nameUrlEmailCode = PublisherData.nameUrlEmailCode();
        me.setPublisherName(nameUrlEmailCode[0]);
        me.setPublisherUrl(nameUrlEmailCode[1]);
        me.setPublisherEmail(nameUrlEmailCode[2]);
        me.setPublisherCode(nameUrlEmailCode[3].toUpperCase());
        me.setAddress(Address.of());
        return me;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Publisher publisher = (Publisher) o;
        return id != null && Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
