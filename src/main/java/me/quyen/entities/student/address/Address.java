package me.quyen.entities.student.address;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import me.quyen.entities.student.Student;
import me.quyen.entities.student.Teacher;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Address {
    @Column(name="streetName")
    private String streetName;
    @Column(name="timeZone")
    private String timeZone;
    @Column(name="cityName")
    private String cityName;
    @Column(name="zipCode")
    private String zipCode;
    @Column(name="state")
    private String state;
    @Column(name="countryCode")
    private String countryCode;
    @Column(name="countryName")
    private String countryName;
    @Column(name="capital")
    private String capital;
    @Column(name="longitude")
    private String longitude;
    @Column(name="latitude")
    private String latitude;

    public static Address of(){
        Faker f = Faker.instance();
        Address me = new Address();
        me.setStreetName(f.address().streetName());
        me.setTimeZone(f.address().timeZone());
        me.setCityName(f.address().cityName());
        me.setZipCode(f.address().zipCode());
        me.setState(f.address().state());
        me.setCountryName(f.country().name());
        me.setCapital(f.country().capital());
        me.setCountryCode(
                f.country().countryCode2()
                        .trim().replace(" ","")
                        .concat("-")
                        .concat(CodeGenerator.code(4,4))
                        .toUpperCase()
        );
        me.setLongitude(f.address().longitude());
        me.setLatitude(f.address().latitude());
        return me;
    }

}
