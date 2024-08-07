package erenculhaci.tunebox.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SubscriptionDTO {
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
