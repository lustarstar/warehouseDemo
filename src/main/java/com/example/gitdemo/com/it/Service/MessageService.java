package com.example.gitdemo.com.it.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gitdemo.com.it.Mapper.MessageMapper;
import com.example.gitdemo.com.it.Mapper.ProductMapper;
import com.example.gitdemo.com.it.Mapper.StoreMapper;
import com.example.gitdemo.com.it.Pojo.Message;
import com.example.gitdemo.com.it.Pojo.Product;
import com.example.gitdemo.com.it.Pojo.ReadStatus;
import com.example.gitdemo.com.it.Pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StoreMapper storeMapper;

    public void sendMessage(Long productId, Integer quantity, Long storeId) {
        Product product = productMapper.selectById(productId);
        Store store = storeMapper.selectById(storeId);

        Message message = new Message();
        message.setOutboundTime(new Date());
        message.setContent(String.format("商品（%s）在门店（%s）出库，数量为 %d", product.getName(), store.getName(), quantity));
        message.setReadStatus(ReadStatus.UNREAD);
        messageMapper.insert(message);

        // 发送短信（伪代码）
        // SmsUtil.sendSmsToAdmin(message.getContent());
    }

    public Page<Message> listMessage(Date startTime, Date endTime, Integer current, Integer size) {
        Page<Message> page = new Page<>(current, size);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        if (startTime != null) {
            wrapper.ge("outbound_time", startTime);
        }
        if (endTime != null) {
            wrapper.le("outbound_time", endTime);
        }
        return messageMapper.selectPage(page, wrapper);
    }

    public void readMessage(Long id) {
        Message message = messageMapper.selectById(id);
        if (message != null && ReadStatus.UNREAD.equals(message.getReadStatus())) {
            message.setReadStatus(ReadStatus.READ);
            messageMapper.updateById(message);

            // 取消短信任务（伪代码）
            // SmsUtil.cancelSmsTask(id);
        }
    }
}

