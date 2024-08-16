package com.example.Sale.Campaign.Management.System.Service;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import com.example.Sale.Campaign.Management.System.Model.Discount;
import com.example.Sale.Campaign.Management.System.Repository.CampaignRepository;
import com.example.Sale.Campaign.Management.System.dto.CampaignDto;
import com.example.Sale.Campaign.Management.System.dto.DiscountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {
    @Autowired
    CampaignRepository campaignRepository;
    public List<CampaignDto> saveAllCampaign(List<Campaign> campaigns){
        for (Campaign campaign:campaigns){
            for (Discount discount:campaign.getDiscounts()) {
                   discount.setCampaign(campaign);
            }
        }

        List<Campaign> campaignList=campaignRepository.saveAll(campaigns);

        List<CampaignDto> list=new ArrayList<>();

        for(Campaign campaign:campaignList){
            CampaignDto campaignDto=new CampaignDto();
            campaignDto.setCampaignId(campaign.getCampaignId());
            campaignDto.setTitle(campaign.getTitle());
            campaignDto.setStartDate(campaign.getStartDate());
            campaignDto.setEndDate(campaign.getEndDate());

            List<DiscountDto> discountDtos=new ArrayList<>();

            for (Discount discount:campaign.getDiscounts()){
                DiscountDto discountDto=new DiscountDto();
                discountDto.setDiscount(discount.getDiscount());
                discountDto.setProductId(discount.getProduct().getId());

                discountDtos.add(discountDto);
            }

                campaignDto.setDiscounts(discountDtos);
            list.add(campaignDto);
        }
        return list;
    }
}
