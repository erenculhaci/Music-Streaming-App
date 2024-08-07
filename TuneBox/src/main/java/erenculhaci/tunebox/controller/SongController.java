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

    @GetMapping("/getAllSongsByGenre")
    public ResponseEntity<List<SongDTO>> getAllSongsByGenre(@RequestParam Long genreId) {
        List<SongDTO> songDTOs = songService.getAllSongsByGenre(genreId);
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/getAllSongsByAlbum")
    public ResponseEntity<List<SongDTO>> getAllSongsByAlbum(@RequestParam Long albumId) {
        List<SongDTO> songDTOs = songService.getAllSongsByAlbum(albumId);
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("/getAllSongsByPlaylist")
    public ResponseEntity<List<SongDTO>> getAllSongsByPlaylist(@RequestParam Long playlistId) {
        List<SongDTO> songDTOs = songService.getAllSongsByPlaylist(playlistId);
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
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
