package com.final_proj.final_proj.controller;

import com.final_proj.final_proj.dto.tests.PassTestRequestDTO;
import com.final_proj.final_proj.dto.tests.StudentTestsDTO;
import com.final_proj.final_proj.dto.tests.TestInfoDTO;
import com.final_proj.final_proj.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/{testId}")
    @ResponseStatus(HttpStatus.OK)
    public TestInfoDTO obtainCertainCourse(@PathVariable Long testId) {
        return testService.obtainCertainCourseById(testId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentTestsDTO> obtainAllStudentTests(Principal principal) {
        return testService.obtainStudentTests(principal.getName());
    }

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.CREATED)
    public Long processStudentAnswers(Principal principal,
                                      @RequestBody PassTestRequestDTO passTestRequest) {
        return testService.processStudentTest(principal.getName(), passTestRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createNewTest(Principal principal,
                              @RequestBody TestInfoDTO testInfo) {

        return testService.createTest(principal.getName(), testInfo);
    }
}
