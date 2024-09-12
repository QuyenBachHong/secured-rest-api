package me.quyen.services.studentbook.book;

import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.book.*;
import me.quyen.entities.student.converter.BookAuthorLibraryPublisherConverter;
import me.quyen.entities.student.dtos.BookDto;
import me.quyen.entities.student.dtos.BookResponse;
import me.quyen.repositories.student.book.*;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {
    final AuthorRepo authorRepo;
    final BookRepo bookRepo;
    final BookAuthorRepo bookAuthorRepo;
    final LibraryRepo libraryRepo;
    final PublisherRepo publisherRepo;
    final BookLibraryRepo bookLibraryRepo;
    final BookPublisherRepo bookPublisherRepo;

    public List<BookResponse> findAllBooks() {
        return bookRepo.findAll().stream().parallel()
                .map(BookResponse::toBookResponse)
                .collect(Collectors.toList());
    }


    public Optional<BookDto> findBookById(@PathVariable("id") Long entityId) {
        Optional<Book> bookOpt = bookRepo.findById(entityId);
        if (bookOpt.isPresent()) {
            BookDto bookDto = BookAuthorLibraryPublisherConverter.bookDto(bookOpt.get());
            return Optional.ofNullable(bookDto);
        }
        return Optional.empty();
    }

    public void create(@RequestBody BookDto bookDto) {
        Objects.requireNonNull(bookDto);
        // save Book
        BookAuthorLibraryPublisherConverter.book(bookDto, bookRepo, authorRepo, libraryRepo, publisherRepo
                , bookAuthorRepo, bookLibraryRepo, bookPublisherRepo);
    }


    public Stream<BookDto> findByBookIsbn(String bookIsbn) {
        return bookRepo.findByBookIsbn(bookIsbn)
                .stream().parallel()
                .map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByBookTitle(String bookTitle) {
        return bookRepo.findByBookTitle(bookTitle)
                .stream().parallel()
                .map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByBookGenre(String bookGenre) {
        return bookRepo.findByBookGenre(bookGenre)
                .stream().parallel()
                .map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByBookPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return bookRepo.findByBookPriceInRange(minPrice, maxPrice)
                .stream().parallel()
                .map(BookAuthorLibraryPublisherConverter::bookDto);
    }



    public Stream<BookDto> findByAuthorFirstNameAndLastName(String firstName, String lastName) {
        return bookRepo.findAllById(
                authorRepo.findByAuthorPersonalDataFirstNameAndLastName(firstName, lastName)
                        .stream().parallel()
                        .map(Author::getBookAuthors)
                        .flatMap(Collection::parallelStream)
                        .map(BookAuthor::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByAuthorFullName(String fullName) {
        return bookRepo.findAllById(
                authorRepo.findByAuthorPersonalDataFullName(fullName)
                        .stream().parallel()
                        .map(Author::getBookAuthors)
                        .flatMap(Collection::parallelStream)
                        .map(BookAuthor::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByAuthorCitizenCode(String citizenCode) {
        return bookRepo.findAllById(
                authorRepo.findByAuthorCitizenCode(citizenCode)
                        .stream().parallel()
                        .map(Author::getBookAuthors)
                        .flatMap(Collection::parallelStream)
                        .map(BookAuthor::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByAuthorPseudonym(String pseudonym) {
        return bookRepo.findAllById(
                authorRepo.findByAuthorPseudonym(pseudonym)
                        .stream().parallel()
                        .map(Author::getBookAuthors)
                        .flatMap(Collection::parallelStream)
                        .map(BookAuthor::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByAuthorAuthorEmail(String authorEmail) {
        return bookRepo.findAllById(
                authorRepo.findByAuthorEmail(authorEmail)
                        .stream().parallel()
                        .map(Author::getBookAuthors)
                        .flatMap(Collection::parallelStream)
                        .map(BookAuthor::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryName(String libraryName) {
        return bookRepo.findAllById(
                libraryRepo.findByName(libraryName)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryCode(String libraryCode) {
        return bookRepo.findAllById(
                libraryRepo.findByCode(libraryCode)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryUrl(String libraryUrl) {
        return bookRepo.findAllById(
                libraryRepo.findByUrl(libraryUrl)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryEmail(String libraryEmail) {
        return bookRepo.findAllById(
                libraryRepo.findByEmail(libraryEmail)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryCityName(String cityName) {
        return bookRepo.findAllById(
                libraryRepo.findByCityName(cityName)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryZipCode(String zipCode) {
        return bookRepo.findAllById(
                libraryRepo.findByZipCode(zipCode)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryCityNameAndZipCode(String cityName, String zipCode) {
        return bookRepo.findAllById(
                libraryRepo.findByCityNameAndZipCode(cityName, zipCode)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByLibraryStateName(String stateName) {
        return bookRepo.findAllById(
                libraryRepo.findByStateName(stateName)
                        .stream().parallel()
                        .map(Library::getBookLibraries)
                        .flatMap(Collection::parallelStream)
                        .map(BookLibrary::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByPublisherName(String publisherName) {
        return bookRepo.findAllById(
                publisherRepo.findByName(publisherName)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByPublisherCode(String publisherCode) {
        return bookRepo.findAllById(
                publisherRepo.findByCode(publisherCode)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByPublisherUrl(String publisherUrl) {
        return bookRepo.findAllById(
                publisherRepo.findByUrl(publisherUrl)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }


    public Stream<BookDto> findByPublisherEmail(String publisherEmail) {
        return bookRepo.findAllById(
                publisherRepo.findByEmail(publisherEmail)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }

    public Stream<BookDto> findByPublisherCityName(String publisherCityName) {
        return bookRepo.findAllById(
                publisherRepo.findByCityName(publisherCityName)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }

    public Stream<BookDto> findByPublisherZipCode(String publisherZipCode) {
        return bookRepo.findAllById(
                publisherRepo.findByZipCode(publisherZipCode)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }

    public Stream<BookDto> findByPublisherCityNameAndZipCode(String cityName, String zipCode) {
        return bookRepo.findAllById(
                publisherRepo.findByCityNameAndZipCode(cityName, zipCode)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }

    public Stream<BookDto> findByPublisherState(String publisherState) {
        return bookRepo.findAllById(
                publisherRepo.findByState(publisherState)
                        .stream().parallel()
                        .map(Publisher::getBookPublishers)
                        .flatMap(Collection::parallelStream)
                        .map(BookPublisher::getBook).map(Book::getId)
                        .collect(Collectors.toSet())
        ).parallelStream().map(BookAuthorLibraryPublisherConverter::bookDto);
    }

//    @GetMapping("/books/pagination-and-sort/title")
//    @GetMapping("/books/pagination-and-sort/title-and-genre")
//    @GetMapping("/books/pagination-and-sort/author-name")
//    @GetMapping("/books/pagination-and-sort/library-name")
//    @GetMapping("/books/pagination-and-sort/publisher-name")


    public int updateByBookIsbn(Long bookId, String bookIsbn) {
        return bookRepo.updateByBookIsbn(bookId, bookIsbn);
    }

    public int updateByBookPrice(Long bookId, BigDecimal bookPrice) {
        return bookRepo.updateByBookPrice(bookId, bookPrice);
    }

    public ResponseEntity<?> deleteById(Long bookId) {
        if (bookRepo.existsById(bookId)) {
            bookRepo.deleteById(bookId);
            return ResponseEntity.ok(
                    String.format("deleted book with id = %s successfully!", bookId)
            );
        }
        return ResponseEntity.ok(
                String.format("book with id = %s NOT existed!", bookId)
        );
    }

    public ResponseEntity<?> deleteByBookIsbn(String bookIsbn) {
        List<Book> books = bookRepo.findByBookIsbn(bookIsbn)
                .stream().collect(Collectors.toList());
        if (books.size() > 0) {
            bookRepo.deleteAll(books);
            return ResponseEntity.ok(
                    String.format("deleted all books with isbn like `%s` successfully!", bookIsbn)
            );
        }
        return ResponseEntity.ok(
                String.format("No book has  isbn like `%s`", bookIsbn)
        );
    }

}
