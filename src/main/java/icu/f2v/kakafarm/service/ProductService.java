package icu.f2v.kakafarm.service;

import icu.f2v.kakafarm.persistence.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> List();
    Product get(int id);
    int addNew(Product product);
    void update(Product product);
    void delete(int id);
}
