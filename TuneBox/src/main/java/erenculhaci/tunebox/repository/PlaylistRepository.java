package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByName(String name);

    List<Playlist> findByUserId(Long userId);

    List<Playlist> findByUserUsername(String username);

    List<Playlist> findBySongsId(Long songId);

    @Query("SELECT g FROM Playlist g JOIN g.songs s WHERE LOWER(s.title) = LOWER(:title)")
    List<Playlist> findBySongsTitle(String title);
}
