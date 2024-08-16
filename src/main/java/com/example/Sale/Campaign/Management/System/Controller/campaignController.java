package com.example.Sale.Campaign.Management.System.Controller;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import com.example.Sale.Campaign.Management.System.Service.CampaignService;
import com.example.Sale.Campaign.Management.System.dto.CampaignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class campaignController {
    @Autowired
    CampaignService campaignService;
    @PostMapping
    public List<CampaignDto> saveCampaign(@RequestBody List<Campaign> campaigns){
        return campaignService.saveAllCampaign(campaigns);
    }
}
