package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Playlist;
import erenculhaci.tunebox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUserId(Long userId);
}
