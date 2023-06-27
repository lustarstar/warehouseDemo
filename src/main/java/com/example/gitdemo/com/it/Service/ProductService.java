package com.example.gitdemo.com.it.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gitdemo.com.it.Mapper.ProductMapper;
import com.example.gitdemo.com.it.Pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public void addProduct(Product product) {
        productMapper.insert(product);
    }

    public void updateProduct(Product product) {
        productMapper.updateById(product);
    }

    public void deleteProduct(Long id) {
        productMapper.deleteById(id);
    }

    public Page<Product> listProduct(String name, Integer current, Integer size) {
        Page<Product> page = new Page<>(current, size);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("create_time");
        return productMapper.selectPage(page, wrapper);
    }
}

