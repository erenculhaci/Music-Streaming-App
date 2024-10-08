package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.SongDTO;
import erenculhaci.tunebox.entity.*;
import erenculhaci.tunebox.repository.*;
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

        Artist artist = album.getArtist();

        Song song = Song.builder()
                .title(songDTO.getTitle())
                .duration(songDTO.getDuration())
                .album(album)
                .genres(genres)
                .artist(artist)
                .build();

        songRepository.save(song);

        return true;
    }

    public SongDTO getSongById(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));
        return convertToDTO(song);
    }

    public SongDTO getSongByTitle(String title) {
        Song song = songRepository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));
        return convertToDTO(song);
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsByGenreId(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        return songRepository.findAllByGenresContains(genre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsByAlbumId(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        return songRepository.findAllByAlbum(album).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsByPlaylistId(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"))
                .getSongs().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SongDTO getRandomSongByGenreName(String genreName) {
        Genre genre = genreRepository.findByNameIgnoreCase(genreName)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        List<Song> songs = songRepository.findAllByGenresContains(genre);
        if(songs.isEmpty()) {
            throw new IllegalArgumentException("No songs found for genre");
        }
        return convertToDTO(songs.get((int) (Math.random() * songs.size())));
    }

    public SongDTO getRandomSongByPlaylistName(String playlistName) {
        Playlist playlist = playlistRepository.findByName(playlistName)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        return convertToDTO(playlist.getSongs().get((int) (Math.random() * playlist.getSongs().size())));
    }

    public SongDTO getRandomSongByAlbumTitle(String albumTitle) {
        List<Album> albums = albumRepository.findAllByTitle(albumTitle);
        if(albums.isEmpty()) {
            throw new IllegalArgumentException("No albums found with title");
        }
        List<Song> songs = songRepository.findAllByAlbum(albums.get(0));
        if(songs.isEmpty()) {
            throw new IllegalArgumentException("No songs found for album");
        }
        return convertToDTO(songs.get((int) (Math.random() * songs.size())));
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

        songRepository.save(song);

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
                .artistId(song.getArtist().getId())
                .build();
    }
}
