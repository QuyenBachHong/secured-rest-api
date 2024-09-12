package me.quyen.entities.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.quyen.entities.student.book.Book;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse implements Serializable {
    private String bookIsbn;
    private String bookTitle;
    private String bookGenre;
    private BigDecimal bookPrice;

    public static BookResponse toBookResponse(Book book){
        return Optional.ofNullable(book).stream().map(src -> {
            BookResponse dsc = new BookResponse();
            BeanUtils.copyProperties(src,dsc);
            return dsc;
        }).findFirst().orElse(null);
    }
}
