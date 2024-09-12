package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {
    private String bookIsbn;
    private String bookTitle;
    private String bookGenre;
    private BigDecimal bookPrice;
    private Set<AuthorDto> authors = new LinkedHashSet<>();
    private Set<LibraryDto> libraries = new LinkedHashSet<>();
    private Set<PublisherDto> publishers = new LinkedHashSet<>();
}
