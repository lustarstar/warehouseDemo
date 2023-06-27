package com.example.gitdemo.com.it.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gitdemo.com.it.Bean.Result;
import com.example.gitdemo.com.it.Pojo.Product;
import com.example.gitdemo.com.it.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Result addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listProduct(@RequestParam(required = false) String name,
                              @RequestParam Integer current,
                              @RequestParam Integer size) {
        Page<Product> page = productService.listProduct(name, current, size);
        return Result.success(page);
    }
}

