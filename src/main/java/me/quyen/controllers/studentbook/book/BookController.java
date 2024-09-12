package me.quyen.controllers.studentbook.book;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.dtos.BookDto;
import me.quyen.entities.student.dtos.BookResponse;
import me.quyen.entities.student.dtos.TeacherResponse;
import me.quyen.services.studentbook.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<BookResponse> bookResponses = bookService.findAllBooks();
        return ResponseEntity.ok(bookResponses);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> findBookById(@PathVariable("id") Long entityId) {
        BookDto bookDto = bookService.findBookById(entityId).orElse(null);
        if (bookDto != null) {
            return ResponseEntity.ok(bookDto);
        }
        return ResponseEntity.ok(String.format("book with id = `%s` NOT existed!", entityId));
    }

    @PostMapping("/books/create")
    public ResponseEntity<?> create(@RequestBody @Valid BookDto bookDto) {
        try {
            bookService.create(bookDto);
            return ResponseEntity.ok("saved book successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("saved book un-successfully!");
        }
    }

    @GetMapping("/books/author/first-name/{firstname}/last-name/{lastname}")
    public ResponseEntity<?> findByAuthorFirstNameAndLastName(@PathVariable("firstname") @Valid String firstName
            , @PathVariable("lastname") @Valid String lastName) {
        List<BookDto> books = bookService.findByAuthorFirstNameAndLastName(firstName, lastName)
                .collect(Collectors.toList());
        if (books.size() > 0) {
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.ok(String.format("No book with first-name like `%s` and last-name like `%s`"
                , firstName, lastName));
    }

    @GetMapping("/books/author/full-name/{fullname}")
    public ResponseEntity<?> findByAuthorFullName(@PathVariable("fullname") @Valid String fullName) {
        List<BookDto> books = bookService.findByAuthorFullName(fullName).collect(Collectors.toList());
        if (books.size() > 0) {
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.ok(String.format("No book with full-name like `%s`", fullName));
    }

    @GetMapping("/books/author/citizen-id/{citizentId}")
    public ResponseEntity<?> findByAuthorCitizenCode(@PathVariable("citizenId") @Valid String citizenCode) {
        List<BookDto> books = bookService.findByAuthorCitizenCode(citizenCode).collect(Collectors.toList());
        if (books.size() > 0) {
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.ok(String.format("No book with citizen-code like `%s`", citizenCode));
    }


    @Transactional(transactionManager = "studentTransactionManager")
    @PutMapping("/books/update-by-isbn/book-id/{id}/book-isbn/{isbn}")
    public ResponseEntity<?> updateByBookIsbn(@PathVariable("id") @Valid Long bookId
            , @PathVariable("isbn") @Valid String bookIsbn) {
        int updated = bookService.updateByBookIsbn(bookId, bookIsbn);
        if (updated > 0) {
            return ResponseEntity.ok(
                    String.format("updated book with id:`%s` and isbn like `%s` successfully!", bookId, bookIsbn)
            );
        }
        return ResponseEntity.ok(
                String.format("updated book with id:`%s` and isbn like `%s` un-successfully!", bookId, bookIsbn)
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            , transactionManager = "studentTransactionManager")
    @PutMapping("/books/update-by-price/book-id/{id}/book-price/{price}")
    public ResponseEntity<?> updateByBookPrice(@PathVariable("id") @Valid Long bookId
            , @PathVariable("price") @Valid BigDecimal bookPrice) {
        int updated = bookService.updateByBookPrice(bookId, bookPrice);
        if (updated > 0) {
            return ResponseEntity.ok(
                    String.format("updated book with id:`%s` and price `%s` successfully!", bookId, bookPrice)
            );
        }
        return ResponseEntity.ok(
                String.format("updated book with id:`%s` and price `%s` un-successfully!", bookId, bookPrice)
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            , transactionManager = "studentTransactionManager")
    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") @Valid Long bookId) {
        return bookService.deleteById(bookId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW
            , transactionManager = "studentTransactionManager")
    @DeleteMapping("/books/delete-by-isbn/{isbn}")
    public ResponseEntity<?> deleteByBookIsbn(@PathVariable("isbn") @Valid String bookIsbn) {
        return bookService.deleteByBookIsbn(bookIsbn);
    }

    //====================================================================================
    @GetMapping("/books/book-isbn/{isbn}")
    public ResponseEntity<?> findByBookIsbn(
            @PathVariable("isbn") @Valid String bookIsbn) {
        List<BookDto> bookDtoList = bookService.findByBookIsbn(bookIsbn)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with book-isbn like `%s`", bookIsbn));
    }

    @GetMapping("/books/book-title/{title}")
    public ResponseEntity<?> findByBookTitle(
            @PathVariable("title") @Valid String bookTitle) {
        List<BookDto> bookDtoList = bookService.findByBookTitle(bookTitle)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with book-title like `%s`", bookTitle));
    }

    @GetMapping("/books/book-genre/{genre}")
    public ResponseEntity<?> findByBookGenre(
            @PathVariable("genre") @Valid String bookGenre) {
        List<BookDto> bookDtoList = bookService.findByBookGenre(bookGenre)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with book-genre like `%s`", bookGenre));
    }

    @GetMapping("/books/book-price/min-price/{min}/max-price/{max}")
    public ResponseEntity<?> findByBookPriceInRange(
            @PathVariable("min") @Valid BigDecimal minPrice, @PathVariable("max") @Valid BigDecimal maxPrice) {
        List<BookDto> bookDtoList = bookService.findByBookPriceInRange(minPrice, maxPrice)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with book-prince in range [%s, %s]", minPrice, maxPrice));
    }

    @GetMapping("/books/author/pseudonym/{pseudonym}")
    public ResponseEntity<?> findByAuthorPseudonym(
            @PathVariable("pseudonym") @Valid String pseudonym) {
        List<BookDto> bookDtoList = bookService.findByAuthorPseudonym(pseudonym)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with author-pseudonym like `%s`", pseudonym));
    }

    @GetMapping("/books/author/email/{email}")
    public ResponseEntity<?> findByAuthorAuthorEmail(
            @PathVariable("email") @Valid String authorEmail) {
        List<BookDto> bookDtoList = bookService.findByAuthorAuthorEmail(authorEmail)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with author-email like `%s`", authorEmail));
    }

    @GetMapping("/books/library/library-name/{name}")
    public ResponseEntity<?> findByLibraryName(
            @PathVariable("name") @Valid String libraryName) {
        List<BookDto> bookDtoList = bookService.findByLibraryName(libraryName)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-name like `%s`", libraryName));
    }

    @GetMapping("/books/library/library-code/{code}")
    public ResponseEntity<?> findByLibraryCode(
            @PathVariable("code") @Valid String libraryCode) {
        List<BookDto> bookDtoList = bookService.findByLibraryCode(libraryCode)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-code like `%s`", libraryCode));
    }

    @GetMapping("/books/library/library-url/{url}")
    public ResponseEntity<?> findByLibraryUrl(
            @PathVariable("url") @Valid String libraryUrl) {
        List<BookDto> bookDtoList = bookService.findByLibraryUrl(libraryUrl)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-url-link like `%s`", libraryUrl));
    }

    @GetMapping("/books/library/library-email/{email}")
    public ResponseEntity<?> findByLibraryEmail(
            @PathVariable("email") @Valid String libraryEmail) {
        List<BookDto> bookDtoList = bookService.findByLibraryEmail(libraryEmail)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-email  like `%s`", libraryEmail));
    }

    @GetMapping("/books/library/library-city-name/{cityName}")
    public ResponseEntity<?> findByLibraryCityName(
            @PathVariable("cityName") @Valid String cityName) {
        List<BookDto> bookDtoList = bookService.findByLibraryCityName(cityName)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-city-name  like `%s`", cityName));
    }

    @GetMapping("/books/library/library-zipcode/{zipCode}")
    public ResponseEntity<?> findByLibraryZipCode(
            @PathVariable("zipCode") @Valid String zipCode) {
        List<BookDto> bookDtoList = bookService.findByLibraryZipCode(zipCode)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-zipcode  like `%s`", zipCode));
    }

    @GetMapping("/books/library/library-city-name/{cityName}/library-zipcode/{zipcode}")
    public ResponseEntity<?> findByLibraryCityNameAndZipCode(
            @PathVariable("cityName") @Valid String cityName
            ,@PathVariable("zipcode") @Valid String zipCode
    ) {
        List<BookDto> bookDtoList = bookService.findByLibraryCityNameAndZipCode(cityName,zipCode)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-city-name like `%s` and library-zip-code  like `%s`"
                        , cityName, zipCode));
    }

    @GetMapping("/books/library/library-state-name/{stateName}")
    public ResponseEntity<?> findByLibraryStateName(
            @PathVariable("stateName") @Valid String stateName) {
        List<BookDto> bookDtoList = bookService.findByLibraryStateName(stateName)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with library-state-name like `%s`", stateName));
    }

    @GetMapping("/books/publisher/publisher-name/{name}")
    public ResponseEntity<?> findByPublisherName(
            @PathVariable("name") @Valid String publisherName) {
        List<BookDto> bookDtoList = bookService.findByPublisherName(publisherName)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-name like `%s`", publisherName));
    }

    @GetMapping("/books/publisher/publisher-code/{code}")
    public ResponseEntity<?> findByPublisherCode(
            @PathVariable("code") @Valid String publisherCode) {
        List<BookDto> bookDtoList = bookService.findByPublisherCode(publisherCode)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-code like `%s`", publisherCode));
    }

    @GetMapping("/books/publisher/publisher-url/{url}")
    public ResponseEntity<?> findByPublisherUrl(
            @PathVariable("url") @Valid String publisherUrl) {
        List<BookDto> bookDtoList = bookService.findByPublisherUrl(publisherUrl)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-url-link like `%s`", publisherUrl));
    }

    @GetMapping("/books/publisher/publisher-email/{email}")
    public ResponseEntity<?> findByPublisherEmail(
            @PathVariable("email") @Valid String publisherEmail) {
        List<BookDto> bookDtoList = bookService.findByPublisherEmail(publisherEmail)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-email like `%s`", publisherEmail));
    }

    @GetMapping("/books/publisher/publisher-city-name/{cityName}")
    public ResponseEntity<?> findByPublisherCityName(
            @PathVariable("cityName") @Valid String publisherCityName) {
        List<BookDto> bookDtoList = bookService.findByPublisherCityName(publisherCityName)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-city-name like `%s`", publisherCityName));
    }

    @GetMapping("/books/publisher/publisher-zipcode/{zipCode}")
    public ResponseEntity<?> findByPublisherZipCode(@PathVariable("zipCode") @Valid String publisherZipCode) {
        List<BookDto> bookDtoList = bookService.findByPublisherZipCode(publisherZipCode)
                .collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-zipcode like `%s`", publisherZipCode));
    }


    @GetMapping("/books/publisher/city-name/{cityName}/zip-code/{zipcode}")
    public ResponseEntity<?> findByPublisherCityNameAndZipCode(
            @PathVariable("cityName") @Valid String cityName
            ,@PathVariable("zipcode") @Valid String zipCode) {
        List<BookDto> bookDtoList = bookService.findByPublisherCityNameAndZipCode(cityName,zipCode
        ).collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-city-name: `%s` and publisher-zip-code: `%s`"
                        , cityName, zipCode));
    }

    @GetMapping("/books/publisher/publisher-state-name/{stateName}")
    public ResponseEntity<?> findByPublisherState(@PathVariable("stateName") @Valid String publisherState) {
        List<BookDto> bookDtoList = bookService.findByPublisherState(publisherState).collect(Collectors.toList());
        if (bookDtoList.size() > 0) {
            return ResponseEntity.ok(bookDtoList);
        }
        return ResponseEntity.ok(
                String.format("no Book-Entity with publisher-state-name like `%s`", publisherState));
    }


//    @GetMapping("/books/pagination-and-sort/title")
//    @GetMapping("/books/pagination-and-sort/title-and-genre")
//    @GetMapping("/books/pagination-and-sort/author-name")
//    @GetMapping("/books/pagination-and-sort/library-name")
//    @GetMapping("/books/pagination-and-sort/publisher-name")
}
