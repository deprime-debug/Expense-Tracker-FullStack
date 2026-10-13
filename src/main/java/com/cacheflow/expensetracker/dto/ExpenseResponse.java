package com.cacheflow.expensetracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpenseResponse {
    private Long id;
    private String name;
    private Double amount;
    private String category;
    private LocalDate date;
    private LocalDateTime createdAt;

    public ExpenseResponse() {
    }

    public ExpenseResponse(Long id, String name, Double amount, String category, LocalDate date, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
