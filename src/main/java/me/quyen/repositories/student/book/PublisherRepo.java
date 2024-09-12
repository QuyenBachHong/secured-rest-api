package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface PublisherRepo extends JpaRepository<Publisher,Long> {


    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Publisher> findAll();


    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Publisher> findAllById(Iterable<Long> PublisherIds);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Publisher> findById(Long publisherId);


    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.publisherName like %:name%")
    Streamable<Publisher> findByName(@Param("name") String publisherName);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.publisherCode like %:code%")
    Streamable<Publisher> findByCode(@Param("code") String publisherCode);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.publisherUrl like %:url%")
    Streamable<Publisher> findByUrl(@Param("url") String publisherUrl);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.publisherEmail like %:email%")
    Streamable<Publisher> findByEmail(@Param("email") String publisherEmail);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.address.cityName like %:CITY%")
    Streamable<Publisher> findByCityName(@Param("CITY") String cityName);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.address.zipCode like %:ZIP_CODE%")
    Streamable<Publisher> findByZipCode(@Param("ZIP_CODE") String zipCode);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.address.cityName like %:CITY% and p.address.zipCode like %:ZIP_CODE%")
    Streamable<Publisher> findByCityNameAndZipCode(@Param("CITY") String cityName
            , @Param("ZIP_CODE") String zipCode);

    @EntityGraph(attributePaths = {"bookPublishers.book"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Publisher p where p.address.state like %:STATE%")
    Streamable<Publisher> findByState(@Param("STATE") String stateName);

}
