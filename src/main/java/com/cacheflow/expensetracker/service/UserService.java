package com.cacheflow.expensetracker.service;

import com.cacheflow.expensetracker.dto.UserResponse;
import com.cacheflow.expensetracker.entity.User;
import com.cacheflow.expensetracker.exception.ResourceNotFoundException;
import com.cacheflow.expensetracker.repository.ExpenseRepository;
import com.cacheflow.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public UserService(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public UserResponse updateSalary(Long userId, Double salary) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setSalary(salary != null ? Math.max(0.0, salary) : 0.0);
        User updated = userRepository.save(user);

        return new UserResponse(
                updated.getId(),
                updated.getUsername(),
                updated.getEmail(),
                updated.getSalary());
    }

    @Transactional(readOnly = true)
    public Double getSalary(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return user.getSalary();
    }

    @Transactional
    public void resetUserData(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setSalary(0.0);
        userRepository.save(user);
        expenseRepository.deleteByUserId(userId);
    }
}
