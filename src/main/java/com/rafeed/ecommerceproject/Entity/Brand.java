package com.rafeed.ecommerceproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "category_id",
            nullable = false
    )
    private Category category;

//    @OneToMany
//    private List<Product> productList;
}
