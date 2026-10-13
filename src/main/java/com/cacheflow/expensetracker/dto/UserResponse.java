package com.cacheflow.expensetracker.dto;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Double salary;

    public UserResponse() {
    }

    public UserResponse(Long id, String username, String email, Double salary) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
