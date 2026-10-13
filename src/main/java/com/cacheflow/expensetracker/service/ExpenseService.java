package com.cacheflow.expensetracker.service;

import com.cacheflow.expensetracker.dto.ExpenseRequest;
import com.cacheflow.expensetracker.dto.ExpenseResponse;
import com.cacheflow.expensetracker.entity.Expense;
import com.cacheflow.expensetracker.entity.User;
import com.cacheflow.expensetracker.exception.ResourceNotFoundException;
import com.cacheflow.expensetracker.repository.ExpenseRepository;
import com.cacheflow.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.Nonnull;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> getUserExpenses(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserIdOrderByDateDescCreatedAtDesc(userId);
        return expenses.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExpenseResponse createExpense(Long userId, ExpenseRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Expense expense = new Expense();
        expense.setName(request.getName().trim());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory().trim());
        expense.setDate(request.getDate() != null ? request.getDate() : LocalDate.now());
        expense.setUser(user);

        Expense saved = expenseRepository.save(expense);
        return mapToResponse(saved);
    }

    @Transactional
    public void deleteExpense(Long userId, Long expenseId) {
        Expense expense = expenseRepository.findByIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        expenseRepository.deleteById(expense.getId());
    }

    @Transactional
    public void deleteAllExpenses(Long userId) {
        expenseRepository.deleteByUserId(userId);
    }

    private ExpenseResponse mapToResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getName(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate(),
                expense.getCreatedAt());
    }
}