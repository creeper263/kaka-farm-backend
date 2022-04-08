package icu.f2v.kakafarm.persistence.repo;

import icu.f2v.kakafarm.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
