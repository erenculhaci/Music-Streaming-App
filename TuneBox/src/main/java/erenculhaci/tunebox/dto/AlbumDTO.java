package erenculhaci.tunebox.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private String title;
    private Long artistId;
}
