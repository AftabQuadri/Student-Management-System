package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.UserAccount;

@Repository

public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {
    // Example of a derived query method
    UserAccount findByUsername(String username);
    List<UserAccount> findAll();
    UserAccount findByRole(String role);
}
