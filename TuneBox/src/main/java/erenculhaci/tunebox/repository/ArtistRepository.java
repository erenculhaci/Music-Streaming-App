package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT a FROM Artist a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) AND LOWER(a.surname) LIKE LOWER(CONCAT('%', :surname, '%'))")
    List<Artist> findAllByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
