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

    // This method searches for artists by name and surname. You can give only name or surname as a parameter too.
    // For example, if you give only name as a parameter, it will return all artists with that name.
    // If you give both name and surname, it will return all artists with that name and surname.
    // If you give only a part of the name or surname, it will return all artists containing that part in their name or surname.
    @GetMapping("/getArtistsByNameAndSurname")
    public ResponseEntity<List<ArtistDTO>> getArtistsByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        List<ArtistDTO> artistsByName = artistService.getArtistsByNameAndSurname(name, surname);
        return new ResponseEntity<>(artistsByName, HttpStatus.OK);
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
