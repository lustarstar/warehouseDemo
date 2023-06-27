package com.example.gitdemo.com.it.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gitdemo.com.it.Mapper.LedgerMapper;
import com.example.gitdemo.com.it.Mapper.ProductMapper;
import com.example.gitdemo.com.it.Pojo.Ledger;
import com.example.gitdemo.com.it.Pojo.OperationType;
import com.example.gitdemo.com.it.Pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LedgerService {
    @Autowired
    private LedgerMapper ledgerMapper;
    @Autowired
    private ProductMapper productMapper;

    public Integer outbound(Long productId, Integer quantity, Long storeId) {
        Ledger ledger = new Ledger();
        ledger.setProductId(productId);
        ledger.setStoreId(storeId);
        ledger.setOperationType(OperationType.OUTBOUND);
        ledger.setOperationQuantity(quantity);
        ledger.setOperationTime(new Date());
        ledgerMapper.insert(ledger);

        // 计算库存
        Integer stock = calculateStock(productId, storeId);
        return stock;
    }

    public Integer inbound(Long productId, Integer quantity, Long storeId) {
        Ledger ledger = new Ledger();
        ledger.setProductId(productId);
        ledger.setStoreId(storeId);
        ledger.setOperationType(OperationType.INBOUND);
        ledger.setOperationQuantity(quantity);
        ledger.setOperationTime(new Date());
        ledgerMapper.insert(ledger);

        // 计算库存
        Integer stock = calculateStock(productId, storeId);
        return stock;
    }

    public Page<Ledger> listInbound(String productName, Date inboundDate, Integer current, Integer size) {
        Page<Ledger> page = new Page<>(current, size);
        QueryWrapper<Ledger> wrapper = new QueryWrapper<>();
        wrapper.eq("operation_type", OperationType.INBOUND);
        if (StringUtils.isNotBlank(productName)) {
            List<Long> productIds = productMapper.selectList(new QueryWrapper<Product>().like("name", productName)).stream().map(Product::getId).collect(Collectors.toList());
            wrapper.in("product_id", productIds);
        }
        if (inboundDate != null) {
            wrapper.ge("operation_time", inboundDate);
        }
        return ledgerMapper.selectPage(page, wrapper);
    }

    public Map<String, Object> statProduct(Long productId) {
        Map<String, Object> data = new HashMap<>();
        // 计算库存
        Integer stock = calculateStock(productId, null);
        data.put("stock", stock);

        // 计算现有商品总价
        Product product = productMapper.selectById(productId);
        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(stock));
        data.put("totalPrice", totalPrice);

        return data;
    }

    private Integer calculateStock(Long productId, Long storeId) {
        QueryWrapper<Ledger> inboundWrapper = new QueryWrapper<>();
        inboundWrapper.eq("product_id", productId).eq("operation_type", OperationType.INBOUND);
        if (storeId != null) {
            inboundWrapper.eq("store_id", storeId);
        }
        int inboundQuantity = Optional.ofNullable(ledgerMapper.selectList(inboundWrapper).stream().mapToInt(Ledger::getOperationQuantity).sum()).orElse(0);

        QueryWrapper<Ledger> outboundWrapper = new QueryWrapper<>();
        outboundWrapper.eq("product_id", productId).eq("operation_type", OperationType.OUTBOUND);
        if (storeId != null) {
            outboundWrapper.eq("store_id", storeId);
        }
        int outboundQuantity = Optional.ofNullable(ledgerMapper.selectList(outboundWrapper).stream().mapToInt(Ledger::getOperationQuantity).sum()).orElse(0);

        return inboundQuantity - outboundQuantity;
    }
}

