package com.final_proj.final_proj.service.implementation;

import com.final_proj.final_proj.dto.UpdateUserInfoDTO;
import com.final_proj.final_proj.dto.UserInfoDTO;
import com.final_proj.final_proj.dto.messages.MessageDTO;
import com.final_proj.final_proj.entity.User;
import com.final_proj.final_proj.entity.UsersGroup;
import com.final_proj.final_proj.mapper.UserMapper;
import com.final_proj.final_proj.repository.UserGroupRepository;
import com.final_proj.final_proj.repository.UserRepository;
import com.final_proj.final_proj.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<User> findUserByLogin(String userLogin) {
        return userRepository.findStudentByEmail(userLogin);
    }

    @Override
    public UserInfoDTO obtainUserInfo(String authenticatedUserEmail) {
        log.info("AUTHENTICATED USER CREDENTIALS ARE {}", authenticatedUserEmail);
        User user = findUserByLogin(authenticatedUserEmail).get();
        Optional<UsersGroup> usersGroup = userGroupRepository.getByUserId(user.getId());

        return userMapper.userToDto(user, usersGroup);
    }

    @Override
    public List<UserInfoDTO> obtainAllStudents() {
        return userGroupRepository.findAll()
                .stream()
                .map(ent -> userMapper.userToDto(ent.getUser(), Optional.of(ent)))
                .toList();
    }

    @Override
    public void updateStudentInfo(Long studentId, UpdateUserInfoDTO updatedInfo) {
        User user = userRepository.getReferenceById(studentId);
        user.setFirstName(updatedInfo.firstName());
        user.setMiddleName(updatedInfo.middleName());
        user.setLastName(updatedInfo.lastName());
        log.info("Updating user with id {} with the following credentials {}", studentId, updatedInfo);
        userRepository.save(user);
    }
}
