package com.example.gitdemo.com.it.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gitdemo.com.it.Bean.Result;
import com.example.gitdemo.com.it.Pojo.Ledger;
import com.example.gitdemo.com.it.Service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/outbound")
    public Result outbound(@RequestParam Long productId,
                           @RequestParam Integer quantity,
                           @RequestParam Long storeId) {
        Integer stock = ledgerService.outbound(productId, quantity, storeId);
        return Result.success(stock);
    }

    @PostMapping("/inbound")
    public Result inbound(@RequestParam Long productId,
                          @RequestParam Integer quantity,
                          @RequestParam Long storeId) {
        Integer stock = ledgerService.inbound(productId, quantity, storeId);
        return Result.success(stock);
    }

    @GetMapping("/listInbound")
    public Result listInbound(@RequestParam(required = false) String productName,
                              @RequestParam(required = false) Date inboundDate,
                              @RequestParam Integer current,
                              @RequestParam Integer size) {
        Page<Ledger> page = ledgerService.listInbound(productName, inboundDate, current, size);
        return Result.success(page);
    }

    @GetMapping("/statProduct")
    public Result statProduct(@RequestParam Long productId) {
        Map<String, Object> data = ledgerService.statProduct(productId);
        return Result.success(data);
    }
}
