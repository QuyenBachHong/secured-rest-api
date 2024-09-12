package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
    private String streetName;
    private String timeZone;
    private String cityName;
    private String zipCode;
    private String state;
    private String countryCode;
    private String countryName;
    private String capital;
}
