package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Album;
import erenculhaci.tunebox.entity.Genre;
import erenculhaci.tunebox.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByGenresContains(Genre genre);
    List<Song> findAllByAlbum(Album album);
}
