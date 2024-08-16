package com.example.Sale.Campaign.Management.System.Service;

import com.example.Sale.Campaign.Management.System.Model.PageDTO;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> saveAllProduct(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public PageDTO getAllWithPagination(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setProducts(productRepository.findAll());
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPages(productPage.getTotalPages());
        return pageDTO;
    }
    public Product deleteById(int id){
        Product p=productRepository.findById(id).get();
         productRepository.deleteById(id);
         return p;
    }
}
