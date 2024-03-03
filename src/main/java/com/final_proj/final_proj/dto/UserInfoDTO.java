package com.final_proj.final_proj.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDTO {
    private Long id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;

    private String positionDesc;

    private String groupCipher;
    private String course;
    private String faculty;
}
