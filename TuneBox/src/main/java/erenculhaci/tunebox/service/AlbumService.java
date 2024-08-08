package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.*;
import erenculhaci.tunebox.entity.*;
import erenculhaci.tunebox.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public Boolean createAlbum(AlbumDTO albumDTO) {
        Artist artist = artistRepository.findById(albumDTO.getArtistId())
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));

        Album album = Album.builder().title(albumDTO.getTitle()).artist(artist).build();
        album = albumRepository.save(album);

        return true;
    }

    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        return convertToDTO(album);
    }

    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByTitle(String title) {
        return albumRepository.findAllByTitleContainsIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByArtistNameAndSurname(String name, String surname) {
        List<Artist> artists = artistRepository.findAllByNameAndSurname(name, surname);
        return albumRepository.findAll().stream()
                .filter(album -> artists.contains(album.getArtist()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByArtistId(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        return albumRepository.findAll().stream()
                .filter(album -> album.getArtist().equals(artist))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Boolean updateAlbum(Long id, AlbumDTO albumDTO) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Artist artist = artistRepository.findById(albumDTO.getArtistId())
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));

        album.setTitle(albumDTO.getTitle());
        album.setArtist(artist);

        album = albumRepository.save(album);

        return true;
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    private AlbumDTO convertToDTO(Album album) {
        return AlbumDTO.builder()
                .title(album.getTitle())
                .artistId(album.getArtist().getId())
                .build();
    }

}