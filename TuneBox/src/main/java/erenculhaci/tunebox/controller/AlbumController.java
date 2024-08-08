package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.*;
import erenculhaci.tunebox.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping("/createAlbum")
    public ResponseEntity<Boolean> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Boolean createdAlbum = albumService.createAlbum(albumDTO);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @GetMapping("/getAlbumById")
    public ResponseEntity<AlbumDTO> getAlbumById(@RequestParam Long id) {
        AlbumDTO album = albumService.getAlbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @GetMapping("/getAllAlbums")
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/getAlbumsByTitle")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByTitle(@RequestParam String title) {
        List<AlbumDTO> albums = albumService.getAlbumsByTitle(title);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/getAlbumsByArtistNameAndSurname")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtistNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        List<AlbumDTO> albums = albumService.getAlbumsByArtistNameAndSurname(name, surname);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/getAlbumsByArtistId")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtistId(@RequestParam Long id) {
        List<AlbumDTO> albums = albumService.getAlbumsByArtistId(id);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PutMapping("/updateAlbum")
    public ResponseEntity<Boolean> updateAlbum(@RequestParam Long id, @RequestBody AlbumDTO albumDTO) {
        Boolean updatedAlbum = albumService.updateAlbum(id, albumDTO);
        return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAlbum")
    public ResponseEntity<Void> deleteAlbum(@RequestParam Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
