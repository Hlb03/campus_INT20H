package com.final_proj.final_proj.controller;

import com.final_proj.final_proj.dto.UpdateUserInfoDTO;
import com.final_proj.final_proj.dto.UserInfoDTO;
import com.final_proj.final_proj.dto.messages.MessageDTO;
import com.final_proj.final_proj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;

    @GetMapping("/user/info")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDTO obtainStudentInfo(Principal principal) {
        return userService.obtainUserInfo(principal.getName());
    }

    @GetMapping("/students/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfoDTO> getAllStudents() {
        return userService.obtainAllStudents();
    }

    @PutMapping("/update/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(@PathVariable Long studentId, @RequestBody UpdateUserInfoDTO updatedInfo) {
        userService.updateStudentInfo(studentId, updatedInfo);
    }
}
