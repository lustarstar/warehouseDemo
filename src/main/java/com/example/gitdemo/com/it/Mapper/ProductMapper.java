package com.example.gitdemo.com.it.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gitdemo.com.it.Pojo.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
