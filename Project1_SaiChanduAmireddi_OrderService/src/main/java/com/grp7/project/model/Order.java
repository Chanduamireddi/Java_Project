package com.grp7.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String stockSymbol;
    private String stockName;
    private Integer quantity;
    private Double pricePerUnit;
    private Double totalPrice;
    private String orderType;
    private String username;
    private LocalDateTime orderDate;
    private String status;
}
