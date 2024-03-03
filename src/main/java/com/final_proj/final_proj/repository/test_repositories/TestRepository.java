package com.final_proj.final_proj.repository.test_repositories;

import com.final_proj.final_proj.entity.tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> getAllByGroup_Id(Long groupId);
}
