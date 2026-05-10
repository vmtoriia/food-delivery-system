package cz.cvut.nss.catalogservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String category;

    private BigDecimal price;

    private boolean available;

    @ManyToOne(optional = false)
    private Restaurant restaurant;
}