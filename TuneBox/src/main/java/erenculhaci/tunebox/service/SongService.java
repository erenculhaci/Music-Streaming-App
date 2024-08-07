package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.SongDTO;
import erenculhaci.tunebox.entity.Album;
import erenculhaci.tunebox.entity.Genre;
import erenculhaci.tunebox.entity.Song;
import erenculhaci.tunebox.repository.AlbumRepository;
import erenculhaci.tunebox.repository.GenreRepository;
import erenculhaci.tunebox.repository.PlaylistRepository;
import erenculhaci.tunebox.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final PlaylistRepository playlistRepository;

    public Boolean createSong(SongDTO songDTO) {
        Album album = albumRepository.findById(songDTO.getAlbumId())
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        List<Genre> genres = genreRepository.findAllById(songDTO.getGenreIds());

        Song song = Song.builder()
                .title(songDTO.getTitle())
                .duration(songDTO.getDuration())
                .album(album)
                .genres(genres)
                .build();

        song = songRepository.save(song);

        return true;
    }

    public SongDTO getSongById(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));
        return convertToDTO(song);
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsByGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        return songRepository.findAllByGenresContains(genre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsByAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        return songRepository.findAllByAlbum(album).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsByPlaylist(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"))
                .getSongs().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Boolean updateSong(Long id, SongDTO songDTO) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));

        Album album = albumRepository.findById(songDTO.getAlbumId())
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        List<Genre> genres = genreRepository.findAllById(songDTO.getGenreIds());

        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setAlbum(album);
        song.setGenres(genres);

        song = songRepository.save(song);

        return true;
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    private SongDTO convertToDTO(Song song) {
        return SongDTO.builder()
                .title(song.getTitle())
                .duration(song.getDuration())
                .albumId(song.getAlbum().getId())
                .genreIds(song.getGenres().stream().map(Genre::getId).collect(Collectors.toList()))
                .build();
    }
}
