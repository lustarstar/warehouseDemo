package com.example.gitdemo.com.it.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gitdemo.com.it.Pojo.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
