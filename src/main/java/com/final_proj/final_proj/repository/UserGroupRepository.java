package com.final_proj.final_proj.repository;

import com.final_proj.final_proj.entity.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UsersGroup, Long> {

    Optional<UsersGroup> getByUserId(Long userId);

    Optional<UsersGroup> getByUserEmail(String userEmail);
}
