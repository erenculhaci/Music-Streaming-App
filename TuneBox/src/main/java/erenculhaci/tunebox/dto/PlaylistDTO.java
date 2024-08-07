package erenculhaci.tunebox.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private String name;
    private Long userId;
    private List<Long> songIds;
}
