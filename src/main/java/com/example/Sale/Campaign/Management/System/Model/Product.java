package com.example.Sale.Campaign.Management.System.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private int mrp;
    private int currentPrice;
    private int discount;
    private int inventoryCount;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Discount>discounts=new ArrayList<>();

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<History> histories=new ArrayList<>();

    public Product(int id) {
        this.id = id;
    }
}
