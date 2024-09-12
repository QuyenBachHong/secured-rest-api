package me.quyen.data_generator;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class CrudEntityTest {

    @Test
    public void addresses() throws IOException {
        Faker f = Faker.instance();
        String streetName =f.address().streetName();
        String timeZone = f.address().timeZone();
        String cityName = f.address().cityName();
        String zipCode = f.address().zipCode();
        String state = f.address().state();
        String country = f.country().name();
        String capital = f.country().capital();
        String countryCode = f.country().countryCode2().toUpperCase();

    }
    @Test
    public void testCountryCode(){
        Faker f = Faker.instance();
        System.out.println(f.country().countryCode2());
    }
}
