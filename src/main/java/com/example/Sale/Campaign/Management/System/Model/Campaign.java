package com.example.Sale.Campaign.Management.System.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int campaignId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;

    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Discount> discounts = new ArrayList<>();

    public Campaign setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
        return this;
    }

    public Campaign setEndDate(String endDate) {
        LocalDate dt = LocalDate.parse(endDate);
        dt = dt.plusDays(1);
        this.endDate = dt;
        return this;
    }

    public void addDiscount(Discount discount) {
        discount.setCampaign(this);
        this.discounts.add(discount);
    }

    public void removeDiscount(Discount discount) {
        discount.setCampaign(null);
        this.discounts.remove(discount);
    }
}