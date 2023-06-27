package com.example.gitdemo.com.it.Pojo;

import java.util.Date;

public class Ledger {
    private Long id;
    private Long productId;
    private Long storeId;
    private OperationType operationType;
    private Integer operationQuantity;
    private Date operationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Integer getOperationQuantity() {
        return operationQuantity;
    }

    public void setOperationQuantity(Integer operationQuantity) {
        this.operationQuantity = operationQuantity;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}
