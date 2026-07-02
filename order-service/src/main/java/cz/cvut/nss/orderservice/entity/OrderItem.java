package cz.cvut.nss.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String menuItemId; // Reference to CatalogService

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;
}