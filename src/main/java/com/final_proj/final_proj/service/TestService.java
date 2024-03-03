package com.final_proj.final_proj.service;

import com.final_proj.final_proj.dto.tests.PassTestRequestDTO;
import com.final_proj.final_proj.dto.tests.StudentTestsDTO;
import com.final_proj.final_proj.dto.tests.TestInfoDTO;

import java.util.List;

public interface TestService {

    TestInfoDTO obtainCertainCourseById(Long courseId);

    List<StudentTestsDTO> obtainStudentTests(String studentEmail);

    Long processStudentTest(String studentEmail, PassTestRequestDTO passTestRequest);

    Long createTest(String creatorEmail, TestInfoDTO testInfo);
}
