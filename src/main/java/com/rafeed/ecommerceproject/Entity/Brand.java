package com.rafeed.ecommerceproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @Column(
            name = "brand_id",
            nullable = false
    )
    @SequenceGenerator(
            name = "brand_id_sequence",
            sequenceName = "brand_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "brand_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private int brandId;

    @Column(
            name = "brand_name",
            unique = true,
            nullable = false
    )
    private String brandName;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "category_id",
            nullable = false
    )
    private Category category;

//    @OneToMany
//    private List<Product> productList;
}
