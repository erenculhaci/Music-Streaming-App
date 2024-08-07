package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.PlaylistDTO;
import erenculhaci.tunebox.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping("/createPlaylist")
    public ResponseEntity<Boolean> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Boolean created = playlistService.createPlaylist(playlistDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getPlaylistById")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@RequestParam Long id) {
        PlaylistDTO playlistDTO = playlistService.getPlaylistById(id);
        return new ResponseEntity<>(playlistDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllPlaylists")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        List <PlaylistDTO> playlistDTOList = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlistDTOList, HttpStatus.OK);
    }

    @GetMapping("/getPlaylistsByUserId")
    public ResponseEntity<List<PlaylistDTO>> getPlaylistsByUserId(@RequestParam Long userId) {
        List<PlaylistDTO> playlistDTOList = playlistService.getPlaylistsByUserId(userId);
        return new ResponseEntity<>(playlistDTOList, HttpStatus.OK);
    }

    @PutMapping("/updatePlaylist")
    public ResponseEntity<Boolean> updatePlaylist(@RequestParam Long id, @RequestBody PlaylistDTO playlistDTO) {
        Boolean updated = playlistService.updatePlaylist(id, playlistDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deletePlaylist")
    public ResponseEntity<Void> deletePlaylist(@RequestParam Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }
}
