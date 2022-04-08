package icu.f2v.kakafarm.controller;

import com.alibaba.fastjson.JSON;
import icu.f2v.kakafarm.persistence.entity.Product;
import icu.f2v.kakafarm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductApi {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public String list() {
        return JSON.toJSONString(productService.List());
    }

    @GetMapping("/get")
    public String get(@RequestParam("id") int id) {
        return JSON.toJSONString(productService.get(id));
    }

    @PostMapping ("/add")
    public void add(@RequestBody Product product) {
        productService.addNew(product);
    }

    @GetMapping ("/delete")
    public void add(@RequestParam("id") int id) {
        productService.delete(id);
    }
}
