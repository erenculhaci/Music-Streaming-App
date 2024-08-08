package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByNameIgnoreCase(String name);
    List<Genre> findBySongs_Id(Long id);

    @Query("SELECT g FROM Genre g JOIN g.songs s WHERE LOWER(s.title) = LOWER(:title)")
    List<Genre> findBySongs_TitleIgnoreCase(@Param("title") String title);
}
