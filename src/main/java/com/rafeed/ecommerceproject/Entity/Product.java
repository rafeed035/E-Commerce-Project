package com.rafeed.ecommerceproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @Column(
            name = "product_id",
            nullable = false
    )
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private int productId;

    @NotBlank
    @Column(
            name = "product_name",
            nullable = false,
            unique = true
    )
    private String productName;

    @NotBlank
    @Column(
            name = "product_specs",
            nullable = false
    )
    private String productSpecs;

    @Column(
            name = "product_description"
    )
    private String productDescription;

    @NotBlank
    @Column(
            name = "product_price",
            nullable = false
    )
    private int productPrice;

    @NotBlank
    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "brand_id",
            nullable = false
    )
    private Brand brand;

    @NotBlank
    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "category_id",
            nullable = false
    )
    private Category category;
}
