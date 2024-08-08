package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.SongDTO;
import erenculhaci.tunebox.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping("/createSong")
    public ResponseEntity<Boolean> createSong(@RequestBody SongDTO songDTO) {
        Boolean created = songService.createSong(songDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getSongById")
    public ResponseEntity<SongDTO> getSongById(@RequestParam Long id) {
        SongDTO songDTO = songService.getSongById(id);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllSongs")
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<SongDTO> songDTOs = songService.getAllSongs();
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/getSongByTitle")
    public ResponseEntity<SongDTO> getSongByTitle(@RequestParam String title) {
        SongDTO songDTO = songService.getSongByTitle(title);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllSongsByGenreId")
    public ResponseEntity<List<SongDTO>> getAllSongsByGenreId(@RequestParam Long genreId) {
        List<SongDTO> songDTOs = songService.getAllSongsByGenreId(genreId);
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/getAllSongsByAlbumId")
    public ResponseEntity<List<SongDTO>> getAllSongsByAlbumId(@RequestParam Long albumId) {
        List<SongDTO> songDTOs = songService.getAllSongsByAlbumId(albumId);
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/getAllSongsByPlaylistId")
    public ResponseEntity<List<SongDTO>> getAllSongsByPlaylistId(@RequestParam Long playlistId) {
        List<SongDTO> songDTOs = songService.getAllSongsByPlaylistId(playlistId);
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/getRandomSongByGenreName")
    public ResponseEntity<SongDTO> getRandomSongByGenreName(@RequestParam String genreName) {
        SongDTO songDTO = songService.getRandomSongByGenreName(genreName);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("/getRandomSongByPlaylistName")
    public ResponseEntity<SongDTO> getRandomSongByPlaylistName(@RequestParam String playlistName) {
        SongDTO songDTO = songService.getRandomSongByPlaylistName(playlistName);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("/getRandomSongByAlbumTitle")
    public ResponseEntity<SongDTO> getRandomSongByAlbumName(@RequestParam String albumTitle) {
        SongDTO songDTO = songService.getRandomSongByAlbumTitle(albumTitle);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @PutMapping("/updateSong")
    public ResponseEntity<Boolean> updateSong(@RequestParam Long id, @RequestBody SongDTO songDTO) {
        Boolean updated = songService.updateSong(id, songDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSong")
    public ResponseEntity<Void> deleteSong(@RequestParam Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
