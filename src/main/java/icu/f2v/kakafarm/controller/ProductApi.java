package icu.f2v.kakafarm.controller;

import com.alibaba.fastjson.JSON;
import icu.f2v.kakafarm.TokenException;
import icu.f2v.kakafarm.persistence.entity.Product;
import icu.f2v.kakafarm.service.JwtService;
import icu.f2v.kakafarm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductApi {
    @Autowired
    ProductService productService;
    @Autowired
    JwtService jwtService;

    @GetMapping("/list")
    public String list() {
        return JSON.toJSONString(productService.List());
    }

    @GetMapping("/get")
    public String get(@RequestParam("id") int id) {
        return JSON.toJSONString(productService.get(id));
    }

    @PostMapping ("/add")
    public String add(@RequestParam String token, @RequestBody Product product) {
        int code = 1;
        try {
            jwtService.verifyToken(token);
        } catch (TokenException e) {
            e.printStackTrace();
            code = 0;
            return JSON.toJSONString(new Response(code));
        }
        productService.addNew(product);
        return JSON.toJSONString(new Response(code));
    }

    @PostMapping ("/update")
    public String update(@RequestParam String token, @RequestBody Product product) {
        int code = 1;
        try {
            jwtService.verifyToken(token);
        } catch (TokenException e) {
            e.printStackTrace();
            code = 0;
            return JSON.toJSONString(new Response(code));
        }
        productService.update(product);
        return JSON.toJSONString(new Response(code));
    }

    @GetMapping ("/delete")
    public String delete(@RequestParam String token, @RequestParam("id") int id) {
        int code = 1;
        try {
            jwtService.verifyToken(token);
        } catch (TokenException e) {
            e.printStackTrace();
            code = 0;
            return JSON.toJSONString(new Response(code));
        }
        productService.delete(id);
        return JSON.toJSONString(new Response(code));
    }

    static class Response {
        int code;

        public Response(int code) {
            this.code = code;
        }
    }
}
