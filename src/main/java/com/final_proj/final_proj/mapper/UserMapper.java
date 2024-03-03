package com.final_proj.final_proj.mapper;

import com.final_proj.final_proj.dto.UserInfoDTO;
import com.final_proj.final_proj.entity.User;
import com.final_proj.final_proj.entity.UsersGroup;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserInfoDTO userToDto(User user, Optional<UsersGroup> usersGroup) {
        return UserInfoDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .positionDesc(user.getPositionName())
                .faculty(usersGroup.map(group -> group.getGroup().getFaculty().getName()).orElse(null))
                .groupCipher(usersGroup.map(group -> group.getGroup().getCipher()).orElse(null))
                .course(usersGroup.map(group -> group.getCourse().name()).orElse(null))
                .build();
    }
}
