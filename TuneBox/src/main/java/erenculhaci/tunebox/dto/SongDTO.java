package erenculhaci.tunebox.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private String title;
    private int duration;
    private Long albumId;
    private List<Long> genreIds;
    private Long artistId;
}
