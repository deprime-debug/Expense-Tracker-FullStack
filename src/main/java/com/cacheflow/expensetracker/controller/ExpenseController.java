package com.cacheflow.expensetracker.controller;

import com.cacheflow.expensetracker.dto.*;
import com.cacheflow.expensetracker.security.UserPrincipal;
import com.cacheflow.expensetracker.service.ExpenseService;
import com.cacheflow.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    // ── Expenses ──────────────────────────────────────────────

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseResponse>> getExpenses(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<ExpenseResponse> expenses = expenseService.getUserExpenses(userPrincipal.getId());
        return ResponseEntity.ok(expenses);
    }

    @PostMapping("/expenses")
    public ResponseEntity<ExpenseResponse> addExpense(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                      @Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse expense = expenseService.createExpense(userPrincipal.getId(), request);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<MessageResponse> deleteExpense(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         @PathVariable Long id) {
        expenseService.deleteExpense(userPrincipal.getId(), id);
        return ResponseEntity.ok(MessageResponse.ok("Expense deleted successfully!"));
    }

    @DeleteMapping("/expenses")
    public ResponseEntity<MessageResponse> deleteAllExpenses(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        expenseService.deleteAllExpenses(userPrincipal.getId());
        return ResponseEntity.ok(MessageResponse.ok("All expenses deleted successfully!"));
    }

    // ── Salary ────────────────────────────────────────────────

    @GetMapping("/salary")
    public ResponseEntity<Double> getSalary(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Double salary = userService.getSalary(userPrincipal.getId());
        return ResponseEntity.ok(salary);
    }

    @PutMapping("/salary")
    public ResponseEntity<UserResponse> updateSalary(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                     @Valid @RequestBody SalaryRequest request) {
        UserResponse response = userService.updateSalary(userPrincipal.getId(), request.getSalary());
        return ResponseEntity.ok(response);
    }

    // ── Reset ─────────────────────────────────────────────────

    @DeleteMapping("/user/reset")
    public ResponseEntity<MessageResponse> resetUserData(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.resetUserData(userPrincipal.getId());
        return ResponseEntity.ok(MessageResponse.ok("User data reset successfully!"));
    }
}
