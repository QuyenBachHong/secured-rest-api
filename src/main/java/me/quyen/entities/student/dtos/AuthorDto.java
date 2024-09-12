package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.fakadata.AuthorData;
import me.quyen.entities.student.PersonalData;
import me.quyen.entities.student.address.Address;
import me.quyen.entities.student.book.Author;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto implements Serializable {
    private String authorCode;
    private String pseudonym;
    private String authorEmail;
    private String authorUrl;
    private PersonalDataDto personalData;
    private AddressDto address;

}
