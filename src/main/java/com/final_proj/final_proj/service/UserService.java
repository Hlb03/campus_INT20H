package com.final_proj.final_proj.service;

import com.final_proj.final_proj.dto.UpdateUserInfoDTO;
import com.final_proj.final_proj.dto.UserInfoDTO;
import com.final_proj.final_proj.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByLogin(String userLogin);

    UserInfoDTO obtainUserInfo(String authenticatedUserEmail);

    List<UserInfoDTO> obtainAllStudents();

    void updateStudentInfo(Long studentId, UpdateUserInfoDTO updatedInfo);
}
