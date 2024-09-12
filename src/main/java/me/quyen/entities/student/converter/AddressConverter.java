package me.quyen.entities.student.converter;

import me.quyen.entities.student.address.Address;
import me.quyen.entities.student.dtos.AddressDto;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class AddressConverter {
    public static AddressDto addressDto(Address address){
        return Optional.ofNullable(address).stream()
                .map(src -> {
                    AddressDto dsc = new AddressDto();
                    BeanUtils.copyProperties(src,dsc);
                    return dsc;
                }).findFirst().orElse(null);
    }

    public static Address address(AddressDto addressDto){
        return Optional.ofNullable(addressDto).stream()
                .map(src -> {
                    Address dsc = new Address();
                    BeanUtils.copyProperties(src,dsc);
                    return dsc;
                }).findFirst().orElse(null);
    }
}
