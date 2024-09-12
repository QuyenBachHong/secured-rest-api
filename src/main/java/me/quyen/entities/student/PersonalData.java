package me.quyen.entities.student;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import me.quyen.entities.fakadata.CodeGenerator;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Getter @Setter @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
@Embeddable
public class PersonalData {
    @Column(name = "fullName",length = 100)
    private String fullName;
    @Column(name = "citizenCode",length = 20)
    private String citizenCode;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "creditLimit")
    private BigDecimal creditLimit;
//    //==========================================
//    @Column(name="streetName")
//    private String streetName;
//    @Column(name="timeZone")
//    private String timeZone;
//    @Column(name="cityName")
//    private String cityName;
//    @Column(name="zipCode")
//    private String zipCode;
//    @Column(name="state")
//    private String state;
//    @Column(name="countryCode")
//    private String countryCode;
//    @Column(name="countryName")
//    private String countryName;
//    @Column(name="capital")
//    private String capital;

    public static PersonalData of(){
        Faker f = Faker.instance();
        PersonalData me = new PersonalData();
        me.setFirstName(firstNameAndLastNames()[0]);
        me.setLastName(firstNameAndLastNames()[1]);
        String fullName = me.getFirstName().concat(" ").concat(me.getLastName());
        me.setFullName(fullName);
        me.setCitizenCode(f.code().gtin13());
        me.setBirthday(CodeGenerator.randomBirthday());
        me.setGender(maleOrFemale());
        me.setPhoneNumber(f.phoneNumber().phoneNumber());
        me.setCreditLimit(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.0,10000.0)));
        //++++++++++++++++++++++
//        me.setStreetName(f.address().streetName());
//        me.setTimeZone(f.address().timeZone());
//        me.setCityName(f.address().cityName());
//        me.setZipCode(f.address().zipCode());
//        me.setState(f.address().state());
//        me.setCountryName(f.country().name());
//        me.setCapital(f.country().capital());
//        me.setCountryCode(
//                f.country().countryCode2()
//                        .trim().replace(" ","")
//                        .concat("-")
//                        .concat(CodeGenerator.code(4,4))
//                        .toUpperCase()
//        );
        return me;
    }
    private static String[] firstNameAndLastNames(){
        Faker f = Faker.instance();
        return new String[]{f.name().firstName(),f.name().lastName()};
    }
    private static String maleOrFemale(){
        return (ThreadLocalRandom.current().nextBoolean())
                ? genders()[0] : genders()[1];
    }
    private static String[] genders(){
        return new String[]{"MALE","FEMALE"};
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "fullName='" + fullName + '\'' +
                ", citizenCode='" + citizenCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
