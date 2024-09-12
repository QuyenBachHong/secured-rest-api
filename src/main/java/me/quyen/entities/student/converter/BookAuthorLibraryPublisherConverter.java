package me.quyen.entities.student.converter;

import me.quyen.entities.student.book.*;
import me.quyen.entities.student.dtos.AuthorDto;
import me.quyen.entities.student.dtos.BookDto;
import me.quyen.entities.student.dtos.LibraryDto;
import me.quyen.entities.student.dtos.PublisherDto;
import me.quyen.repositories.student.book.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookAuthorLibraryPublisherConverter {
    public static AuthorDto authorDto(Author author){
        return Optional.ofNullable(author)
                .stream().map(src -> {
                    AuthorDto dsc = new AuthorDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setPersonalData(PersonalDataConverter.personalDataDto(src.getPersonalData()));
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static Author author(AuthorDto authorDto){
        return Optional.ofNullable(authorDto).stream().map(src -> {
            Author dsc = new Author();
            BeanUtils.copyProperties(src,dsc);
            dsc.setPersonalData(PersonalDataConverter.personalData(src.getPersonalData()));
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            return dsc;
        }).findFirst().orElse(null);
    }
    public static LibraryDto libraryDto(Library library){
        return Optional.ofNullable(library)
                .stream().map(src -> {
                    LibraryDto dsc = new LibraryDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static Library library(LibraryDto libraryDto){
        return Optional.ofNullable(libraryDto).stream().map(src -> {
            Library dsc = new Library();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            return dsc;
        }).findFirst().orElse(null);
    }
    public static PublisherDto publisherDto(Publisher publisher){
        return Optional.ofNullable(publisher)
                .stream().map(src -> {
                    PublisherDto dsc = new PublisherDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static Publisher publisher(PublisherDto publisherDto){
        return Optional.ofNullable(publisherDto).stream().map(src -> {
            Publisher dsc = new Publisher();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            return dsc;
        }).findFirst().orElse(null);
    }
    public static BookDto bookDto(Book book){
        return Optional.ofNullable(book)
                .stream().map(src -> {
                    var dsc = new BookDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.getAuthors().addAll(
                            src.getBookAuthors()
                            .stream().map(BookAuthor::getAuthor)
                            .map(BookAuthorLibraryPublisherConverter::authorDto).toList());
                    dsc.getLibraries().addAll(
                            src.getBookLibraries().stream().map(BookLibrary::getLibrary)
                                    .map(BookAuthorLibraryPublisherConverter::libraryDto).toList()
                    );
                    dsc.getPublishers().addAll(
                            src.getBookPublishers().stream().map(BookPublisher::getPublisher)
                                    .map(BookAuthorLibraryPublisherConverter::publisherDto).toList()
                    );
                    return dsc;
                }).findFirst().orElse(null);
    }

    public static Book book(BookDto bookDto
            , BookRepo bookRepo, AuthorRepo authorRepo, LibraryRepo libraryRepo, PublisherRepo publisherRepo
            ,BookAuthorRepo bookAuthorRepo,BookLibraryRepo bookLibraryRepo,BookPublisherRepo bookPublisherRepo){
        return Optional.ofNullable(bookDto)
                .stream().map(src -> {
                    Book dsc = new Book();
                    BeanUtils.copyProperties(src,dsc);
                    // Author
                    List<Author> authors = src.getAuthors().stream()
                            .map(BookAuthorLibraryPublisherConverter::author).collect(Collectors.toList());
                    authorRepo.saveAll(authors);
                    // Library
                    List<Library> libraries = src.getLibraries().stream()
                            .map(BookAuthorLibraryPublisherConverter::library).collect(Collectors.toList());
                    libraryRepo.saveAll(libraries);
                    // Publisher
                    List<Publisher> publishers = src.getPublishers().stream().map(BookAuthorLibraryPublisherConverter::publisher)
                            .collect(Collectors.toList());
                    publisherRepo.saveAll(publishers);
                    // Book
                    bookRepo.save(dsc);
                    // BookAuthor
                    bookAuthorRepo.saveAll(authors.stream().map(author -> BookAuthor.of(dsc,author)).collect(Collectors.toList()));
                    // BookLibrary
                    bookLibraryRepo.saveAll(
                        libraries.stream().map(lib -> BookLibrary.of(dsc,lib)).collect(Collectors.toList())
                    );
                    // BookPublisher
                    bookPublisherRepo.saveAll(
                      publishers.stream().map(pub -> BookPublisher.of(dsc,pub)).collect(Collectors.toList())
                    );
                    return dsc;
                }).findFirst().orElse(null);
    }
}
