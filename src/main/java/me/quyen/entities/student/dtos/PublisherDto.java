package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.address.Address;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto implements Serializable {
    private String publisherCode;
    private String publisherName;
    private String publisherUrl;
    private String publisherEmail;
    private AddressDto address;
}
