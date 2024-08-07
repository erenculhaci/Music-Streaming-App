package erenculhaci.tunebox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
