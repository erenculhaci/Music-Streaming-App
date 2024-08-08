package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.GenreDTO;
import erenculhaci.tunebox.entity.Genre;
import erenculhaci.tunebox.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public Boolean createGenre(GenreDTO genreDTO) {
        Genre genre = modelMapper.map(genreDTO, Genre.class);
        genreRepository.save(genre);
        return true;
    }

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        return convertToDTO(genre);
    }

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GenreDTO getGenreByName(String name) {
        Genre genre = genreRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        return convertToDTO(genre);
    }

    public List<GenreDTO> getGenresBySongId(Long id) {
        return genreRepository.findBySongs_Id(id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<GenreDTO> getGenresBySongTitle(String title) {
        return genreRepository.findBySongs_TitleIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Boolean updateGenre(Long id, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        genre.setName(genreDTO.getName());
        genreRepository.save(genre);
        return true;
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    private GenreDTO convertToDTO(Genre genre) {
        return GenreDTO.builder()
                .name(genre.getName())
                .build();
    }
}