package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.ArtistDTO;
import erenculhaci.tunebox.entity.Artist;
import erenculhaci.tunebox.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    public Boolean createArtist(ArtistDTO artistDTO) {
        Artist artist = modelMapper.map(artistDTO, Artist.class);
        artistRepository.save(artist);
        return true;
    }

    public ArtistDTO getArtistById(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        return convertToDTO(artist);
    }

    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArtistDTO> getArtistsByNameAndSurname(String name, String surname) {
        return artistRepository.findAllByNameAndSurname(name, surname).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Boolean updateArtist(Long id, ArtistDTO artistDTO) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        artist.setName(artistDTO.getName());
        artist.setSurname(artistDTO.getSurname());
        artistRepository.save(artist);
        return true;
    }



    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    private ArtistDTO convertToDTO(Artist artist) {
        return ArtistDTO.builder()
                .name(artist.getName())
                .surname(artist.getSurname())
                .build();
    }
}
