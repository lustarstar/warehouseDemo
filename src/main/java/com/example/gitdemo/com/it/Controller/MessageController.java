package com.example.gitdemo.com.it.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gitdemo.com.it.Bean.Result;
import com.example.gitdemo.com.it.Pojo.Message;
import com.example.gitdemo.com.it.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public Result listMessage(@RequestParam(required = false) Date startTime,
                              @RequestParam(required = false) Date endTime,
                              @RequestParam Integer current,
                              @RequestParam Integer size) {
        Page<Message> page = messageService.listMessage(startTime, endTime, current, size);
        return Result.success(page);
    }

    @PutMapping("/read/{id}")
    public Result readMessage(@PathVariable Long id) {
        messageService.readMessage(id);
        return Result.success();
    }
}

