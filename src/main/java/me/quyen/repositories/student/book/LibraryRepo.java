package me.quyen.repositories.student.book;

import me.quyen.entities.student.book.Library;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface LibraryRepo extends JpaRepository<Library,Long> {


    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Library> findAll();

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Library> findAllById(Iterable<Long> ids);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Library> findById(Long id);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.libraryName like %:name%")
    Streamable<Library> findByName(@Param("name") String libraryName);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.libraryCode like %:code%")
    Streamable<Library> findByCode(@Param("code") String libraryCode);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.libraryUrl like %:url%")
    Streamable<Library> findByUrl(@Param("url") String libraryUrl);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.libraryUrl like %:EMAIL%")
    Streamable<Library> findByEmail(@Param("EMAIL") String libraryEmail);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.address.cityName like %:CITY%")
    Streamable<Library> findByCityName(@Param("CITY") String cityName);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.address.zipCode like %:ZIP_CODE%")
    Streamable<Library> findByZipCode(@Param("ZIP_CODE") String zipCode);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.address.cityName like %:CITY% and l.address.zipCode like %:ZIP_CODE%")
    Streamable<Library> findByCityNameAndZipCode(
            @Param("CITY") String cityName, @Param("ZIP_CODE") String zipCode);

    @EntityGraph(attributePaths = {"bookLibraries.book","address"}
            , type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Library l where l.address.state like %:STATE%")
    Streamable<Library> findByStateName(@Param("STATE") String stateName);
}
