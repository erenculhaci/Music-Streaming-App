package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.ArtistDTO;
import erenculhaci.tunebox.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping("/createArtist")
    public ResponseEntity<Boolean> createArtist(@RequestBody ArtistDTO artistDTO) {
        Boolean created = artistService.createArtist(artistDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getArtistById")
    public ResponseEntity<ArtistDTO> getArtistById(@RequestParam Long id) {
        ArtistDTO artistById = artistService.getArtistById(id);
        return new ResponseEntity<>(artistById, HttpStatus.OK);
    }

    @GetMapping("/getAllArtists")
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<ArtistDTO> allArtists = artistService.getAllArtists();
        return new ResponseEntity<>(allArtists, HttpStatus.OK);
    }

    @PutMapping("/updateArtist")
    public ResponseEntity<Boolean> updateArtist(@RequestParam Long id, @RequestBody ArtistDTO artistDTO) {
        Boolean updated = artistService.updateArtist(id, artistDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteArtist")
    public ResponseEntity<Void> deleteArtist(@RequestParam Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
