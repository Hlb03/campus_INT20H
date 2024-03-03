package com.final_proj.final_proj.repository.test_repositories;

import com.final_proj.final_proj.entity.tests.StudentTestAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentTestAttemptsRepository extends JpaRepository<StudentTestAttempts, Long> {

    List<StudentTestAttempts> findAllByUserId(Long studentId);
}
