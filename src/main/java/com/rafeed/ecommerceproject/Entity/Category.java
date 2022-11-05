package com.rafeed.ecommerceproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @Column(
            name = "category_id",
            nullable = false
    )
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "category_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private int categoryId;

    @Column(
            name = "category_name",
            nullable = false
    )
    private String categoryName;

//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "category"
//    )
//    private List<Brand>brands;
//
//    @OneToMany
//    private List<Product>productList;
}
