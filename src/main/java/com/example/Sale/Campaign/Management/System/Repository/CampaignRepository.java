package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    @Query(value = "select * from campaign where start_date=current_date()", nativeQuery = true)
    List<Object[]> getCampaignStartById();
    @Query(value = "select * from campaign where end_date=current_date()", nativeQuery = true)
    List<Object[]> getCampaignStopById();
}
