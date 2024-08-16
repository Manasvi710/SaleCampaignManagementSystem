package com.example.Sale.Campaign.Management.System.Model;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int discountId;
    private int discount;

    @ManyToOne
        private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Campaign campaign;
}