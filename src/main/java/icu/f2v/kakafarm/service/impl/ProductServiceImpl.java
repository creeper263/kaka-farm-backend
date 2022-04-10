package icu.f2v.kakafarm.service.impl;

import icu.f2v.kakafarm.persistence.entity.Product;
import icu.f2v.kakafarm.persistence.repo.ProductRepository;
import icu.f2v.kakafarm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> List() {
        List<Product> productList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productList.add(product);
        }
        return productList;
    }

    public Product get(int id) {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        } else {
            return product.get();
        }
    }

    public int addNew(Product product) {
        int id;
        do {
            id = newID();
        } while(productRepository.findById(id).isPresent());
        product.setId(id);
        productRepository.save(product);
        return id;
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public int newID() {
        var random = new Random();
        int num = random.nextInt(100000000);
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.append("0".repeat(9 - sb.length()));
        return Integer.parseInt(sb.toString());
    }
}
