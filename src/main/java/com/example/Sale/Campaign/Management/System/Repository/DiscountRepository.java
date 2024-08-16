    package com.example.Sale.Campaign.Management.System.Repository;

    import com.example.Sale.Campaign.Management.System.Model.Discount;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Objects;
@Repository
    public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    @Query("SELECT d, p FROM Discount d JOIN d.product p WHERE d.campaign.id = :campaignId")
    List<Object[]> getDiscountsByCampaignId(@Param("campaignId") int campaignId);
    }
