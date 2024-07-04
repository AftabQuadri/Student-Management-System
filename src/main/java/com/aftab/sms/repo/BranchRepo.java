package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Branch;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
    @Query("SELECT DISTINCT b.branchName FROM Branch b")
    List<String> findDistinctBranchNames();
    
    List<Branch> findByCourseName(String courseName);
    
    Branch findByBranchName(String branchName);
    
    

}
