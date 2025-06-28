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
@Table(name = "wishlist_details",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_wishlist", columnNames = {"wishlist_id", "product_id"})
        })
public class WishlistDetail {

    @Id
    @Column(name = "id", length = 40)
    private String id;

    @Column(name = "wishlist_id", length = 40, nullable = false)
    private String wishlistId;

    @Column(name = "product_id", length = 40, nullable = false)
    private String productId;

    @Column(name = "added_at", nullable = false)
    @Builder.Default
    private OffsetDateTime addedAt = OffsetDateTime.now();

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Wishlist wishlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductVariant productVariant;
}
