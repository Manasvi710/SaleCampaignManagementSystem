package com.example.Sale.Campaign.Management.System.Scheduler;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import com.example.Sale.Campaign.Management.System.Model.Discount;
import com.example.Sale.Campaign.Management.System.Model.History;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Repository.CampaignRepository;
import com.example.Sale.Campaign.Management.System.Repository.DiscountRepository;
import com.example.Sale.Campaign.Management.System.Repository.HistoryRepository;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@Configuration
@EnableScheduling
public class userScheduler {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    CampaignRepository campaignRepository;

    @Scheduled(cron = "0 0 0 * * *")
//    @PostConstruct
    public void campaignStart(){
        System.out.println("Sale is Starting...");
        List<Object[]>campList=campaignRepository.getCampaignStartById();
        if(campList.isEmpty())
            return;

        System.out.println("campaign start.....");
        for (Object[] campaignI: campList){
            Campaign campaign=campaignRepository.findById((int)campaignI[0]).get();
            List<Discount> discountList=campaign.getDiscounts();

            for(Discount discount:discountList){
                Product product=productRepository.findById(discount.getProduct().getId()).get();
                int dis=discount.getDiscount();

                int oldCur=product.getCurrentPrice();
                int newPrice=((product.getMrp() * dis) / 100);
                product.setCurrentPrice((oldCur-newPrice));
                product.setDiscount(product.getDiscount() + dis);
                productRepository.save(product);
                System.out.println("Product save..");

                History history=new History();
                history.setDate(LocalDate.now());
                history.setBeforeDiscount(oldCur);
                history.setAfterDiscount(product.getCurrentPrice());
                history.setProduct(product);
                historyRepository.save(history);
                System.out.println("History saved");
            }
        }
    }
    @Scheduled(cron = "0 59 23 * * *")
    public void campaignStop() {
        List<Object[]> campList = campaignRepository.getCampaignStopById();
        if (campList.isEmpty())
            return;

        System.out.println("campaign stop.....");
        for (Object[] campaignI : campList) {
            Campaign campaign = campaignRepository.findById((int)campaignI[0]).get();
            List<Discount> discountList =campaign.getDiscounts() ;

            for(Discount discount:discountList){
                Product product=productRepository.findById(discount.getProduct().getId()).get();
                int dis=discount.getDiscount();

                int oldCur = product.getCurrentPrice();
                int newPrice = ((product.getMrp() * dis) / 100);
                product.setCurrentPrice((oldCur + newPrice));
                product.setDiscount(product.getDiscount() - dis);
                productRepository.save(product);
                System.out.println("Product save..");

                History history = new History();
                history.setDate(LocalDate.now());
                history.setBeforeDiscount(oldCur);
                history.setAfterDiscount(product.getCurrentPrice());
                history.setProduct(product);
                historyRepository.save(history);
                System.out.println("History saved");
            }
        }
    }

}
