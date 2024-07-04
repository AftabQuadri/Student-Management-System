package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Library;
@Repository

public interface LibraryRepo extends JpaRepository<Library, Long> {
    // Example of a derived query method
    List<Library> findByLibraryId(Long id);
    // Example of a custom query method using JPQL
    @Query("SELECT l FROM Library l WHERE l.bookTitle LIKE %:keyword%")
    List<Library> findLibrariesByKeyword(@Param("keyword") String keyword);
}
