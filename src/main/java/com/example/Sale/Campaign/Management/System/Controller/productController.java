package com.example.Sale.Campaign.Management.System.Controller;

import com.example.Sale.Campaign.Management.System.Model.PageDTO;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    ProductService productService;
    @PostMapping
    public List<Product> saveAllProduct(@RequestBody List<Product> products){
        return productService.saveAllProduct(products);
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Product deleteById(@PathVariable("id") int id){
        return productService.deleteById(id);
    }

    @GetMapping("Product/{page}/{pageSize}")
    public PageDTO getProductWithPagination(@RequestParam(defaultValue = "100") int page, @PathVariable int pageSize){
        return productService.getAllWithPagination(page, pageSize);
    }
}
