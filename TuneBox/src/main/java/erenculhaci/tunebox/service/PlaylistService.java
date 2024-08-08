package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.PlaylistDTO;
import erenculhaci.tunebox.entity.Playlist;
import erenculhaci.tunebox.entity.Song;
import erenculhaci.tunebox.entity.User;
import erenculhaci.tunebox.repository.PlaylistRepository;
import erenculhaci.tunebox.repository.SongRepository;
import erenculhaci.tunebox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public Boolean createPlaylist(PlaylistDTO playlistDTO) {
        User user = userRepository.findById(playlistDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Song> songs = songRepository.findAllById(playlistDTO.getSongIds());

        Playlist playlist = Playlist.builder()
                .name(playlistDTO.getName())
                .user(user)
                .songs(songs)
                .build();

        playlistRepository.save(playlist);

        return true;
    }

    public PlaylistDTO getPlaylistById(Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        return convertToDTO(playlist);
    }

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PlaylistDTO> getPlaylistsByUserId(Long userId) {
        List<Playlist> playlists = playlistRepository.findByUserId(userId);
        return playlists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PlaylistDTO> getPlaylistsByUsername(String username) {
        List<Playlist> playlists = playlistRepository.findByUserUsername(username);
        return playlists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PlaylistDTO> getPlaylistsBySongId(Long songId) {
        List<Playlist> playlists = playlistRepository.findBySongsId(songId);
        return playlists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PlaylistDTO> getPlaylistsBySongTitle(String title) {
        List<Playlist> playlists = playlistRepository.findBySongsTitle(title);
        return playlists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Boolean updatePlaylist(Long id, PlaylistDTO playlistDTO) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));

        User user = userRepository.findById(playlistDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Song> songs = songRepository.findAllById(playlistDTO.getSongIds());

        playlist.setName(playlistDTO.getName());
        playlist.setUser(user);
        playlist.setSongs(songs);

        playlistRepository.save(playlist);

        return true;
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    private PlaylistDTO convertToDTO(Playlist playlist) {
        return PlaylistDTO.builder()
                .name(playlist.getName())
                .userId(playlist.getUser().getId())
                .songIds(playlist.getSongs().stream().map(song -> song.getId()).collect(Collectors.toList()))
                .build();
    }
}

