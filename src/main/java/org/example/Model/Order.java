package org.example.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String orderId;
    private String customerId;
    @DBRef
    private List<PackageEntity> packages;
    private LocalDateTime orderDate;
    private String uuid;
    private String ownershipFlag;

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<PackageEntity> getPackages() { return packages; }
    public void setPackages(List<PackageEntity> packages) { this.packages = packages; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
    public String getOwnershipFlag() { return ownershipFlag; }
    public void setOwnershipFlag(String ownershipFlag) { this.ownershipFlag = ownershipFlag; }
}

