package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.address.Address;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto implements Serializable {
    private String libraryCode;
    private String libraryName;
    private String libraryUrl;
    private String libraryEmail;
    private AddressDto address;
}
