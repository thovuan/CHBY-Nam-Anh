package com.chys.WebCHYS.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_variants",
        indexes = {
                @Index(name = "idx_product_variants_product_id", columnList = "product_id"),
                @Index(name = "idx_product_variants_sku", columnList = "sku")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_sku", columnNames = "sku")
        })
public class ProductVariant {

    @Id
    @Column(name = "id", length = 40)
    private String id;

    @Column(name = "product_id", length = 40, nullable = false)
    private String productId;

    @Column(name = "product_variant_name", length = 100, nullable = false)
    private String productVariantName;

    @Column(name = "color_id", length = 40)
    private String colorId;

    @Column(name = "size_id", length = 40)
    private String sizeId;

    @Column(name = "weight", precision = 8, scale = 3)
    private BigDecimal weight;

    @Column(name = "sku", length = 50, unique = true, nullable = false)
    private String sku;

    @Column(name = "stock_quantity", nullable = false)
    @Builder.Default
    private Integer stockQuantity = 0;

    @Column(name = "price_adjustment", precision = 12, scale = 2, nullable = false)
    @Builder.Default
    private BigDecimal priceAdjustment = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private ProductStatus isActive = ProductStatus.ACTIVE;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WishlistDetail> wishlistDetails;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartDetail> cartDetails;
}
