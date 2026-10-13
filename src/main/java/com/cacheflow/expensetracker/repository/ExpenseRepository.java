package com.cacheflow.expensetracker.repository;

import com.cacheflow.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Pulls all expenses for a user, sorted newest first
    List<Expense> findByUserIdOrderByDateDescCreatedAtDesc(Long userId);

    // Find a specific expense belonging to a specific user
    Optional<Expense> findByIdAndUserId(Long id, Long userId);

    // Pulls user expenses filtered by a specific budget category
    List<Expense> findByUserIdAndCategory(Long userId, String category);

    // Pulls all expenses for a user (unsorted)
    List<Expense> findByUserId(Long userId);

    // Delete all expenses for a user
    void deleteByUserId(Long userId);
}
