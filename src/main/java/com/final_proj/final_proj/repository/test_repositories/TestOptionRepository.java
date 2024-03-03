package com.final_proj.final_proj.repository.test_repositories;

import com.final_proj.final_proj.entity.tests.TestOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestOptionRepository extends JpaRepository<TestOption, Long> {

    List<TestOption> getAllByTestId(Long testId);
}
