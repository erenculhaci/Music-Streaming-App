package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.GenreDTO;
import erenculhaci.tunebox.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/createGenre")
    public ResponseEntity<Boolean> createGenre(@RequestBody GenreDTO genreDTO) {
        Boolean created = genreService.createGenre(genreDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getGenreById")
    public ResponseEntity<GenreDTO> getGenreById(@RequestParam Long id) {
        GenreDTO genreDTO = genreService.getGenreById(id);
        return new ResponseEntity<>(genreDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllGenres")
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genreDTOList = genreService.getAllGenres();
        return new ResponseEntity<>(genreDTOList, HttpStatus.OK);
    }

    @GetMapping("/getGenreByName")
    public ResponseEntity<GenreDTO> getGenreByName(@RequestParam String name) {
        GenreDTO genreDTO = genreService.getGenreByName(name);
        return new ResponseEntity<>(genreDTO, HttpStatus.OK);
    }

    @GetMapping("/getGenresBySongId")
    public ResponseEntity<List<GenreDTO>> getGenresBySongId(@RequestParam Long id) {
        List<GenreDTO> genreDTOList = genreService.getGenresBySongId(id);
        return new ResponseEntity<>(genreDTOList, HttpStatus.OK);
    }

    @GetMapping("/getGenresBySongTitle")
    public ResponseEntity<List<GenreDTO>> getGenresBySongTitle(@RequestParam String title) {
        List<GenreDTO> genreDTOList = genreService.getGenresBySongTitle(title);
        return new ResponseEntity<>(genreDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateGenre")
    public ResponseEntity<Boolean> updateGenre(@RequestParam Long id, @RequestBody GenreDTO genreDTO) {
        Boolean updated = genreService.updateGenre(id, genreDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteGenre")
    public ResponseEntity<Void> deleteGenre(@RequestParam Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
