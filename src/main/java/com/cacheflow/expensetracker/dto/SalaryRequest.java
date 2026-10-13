package com.cacheflow.expensetracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class SalaryRequest {
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary cannot be negative")
    private Double salary;

    public SalaryRequest() {
    }

    public SalaryRequest(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
