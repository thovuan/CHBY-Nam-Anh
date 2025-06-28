package com.chys.WebCHYS.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_details",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_cart_details", columnNames = {"cart_id", "product_variant_id"})
        })
public class CartDetail {
    @Id
    @Column(name = "id", length = 40)
    private String id;

    @Column(name = "cart_id", length = 40, nullable = false)
    private String cartId;

    @Column(name = "product_variant_id", length = 40, nullable = false)
    private String productVariantId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "added_at", nullable = false)
    @Builder.Default
    private OffsetDateTime addedAt = OffsetDateTime.now();

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductVariant productVariant;

}
