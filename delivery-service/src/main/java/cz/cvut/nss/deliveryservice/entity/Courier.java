package cz.cvut.nss.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // [cite: 81]

    @Column(nullable = false)
    private String name; // [cite: 82]

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourierStatus status; // [cite: 83]
}