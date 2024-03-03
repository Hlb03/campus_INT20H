package com.final_proj.final_proj.repository;

import com.final_proj.final_proj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findStudentByEmail(String email);
}
