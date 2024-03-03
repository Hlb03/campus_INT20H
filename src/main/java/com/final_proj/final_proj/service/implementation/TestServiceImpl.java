package com.final_proj.final_proj.service.implementation;

import com.final_proj.final_proj.dto.tests.PassTestRequestDTO;
import com.final_proj.final_proj.dto.tests.StudentTestsDTO;
import com.final_proj.final_proj.dto.tests.TestInfoDTO;
import com.final_proj.final_proj.dto.tests.TestOptionDTO;
import com.final_proj.final_proj.entity.Group;
import com.final_proj.final_proj.entity.User;
import com.final_proj.final_proj.entity.UsersGroup;
import com.final_proj.final_proj.entity.tests.StudentTestAttempts;
import com.final_proj.final_proj.entity.tests.Subject;
import com.final_proj.final_proj.entity.tests.Test;
import com.final_proj.final_proj.entity.tests.TestOption;
import com.final_proj.final_proj.repository.UserGroupRepository;
import com.final_proj.final_proj.repository.UserRepository;
import com.final_proj.final_proj.repository.test_repositories.StudentTestAttemptsRepository;
import com.final_proj.final_proj.repository.test_repositories.TestOptionRepository;
import com.final_proj.final_proj.repository.test_repositories.TestRepository;
import com.final_proj.final_proj.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestOptionRepository testOptionRepository;
    private final StudentTestAttemptsRepository studentTestAttemptsRepository;

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;


    @Override
    public TestInfoDTO obtainCertainCourseById(Long courseId) {
        Test test = testRepository.getReferenceById(courseId);
        List<TestOption> options = testOptionRepository.getAllByTestId(courseId);
        log.info("Obtaining full course info with id {}", courseId);
        return TestInfoDTO.builder()
                .id(test.getId())
                .theme(test.getTheme())
                .deadline(test.getDeadline())
                .options(
                        options.stream()
                                .map(testOption -> TestOptionDTO.builder()
                                        .question(testOption.getQuestion())
                                        .option1(testOption.getOption1())
                                        .option2(testOption.getOption2())
                                        .option3(testOption.getOption3())
                                        .option4(testOption.getOption4())
                                        .correctOption(testOption.getCorrectOption())
                                        .build()
                                )
                                .toList()
                )
                .build();
    }

    @Override
    public List<StudentTestsDTO> obtainStudentTests(String studentEmail) {
        UsersGroup userGroup = userGroupRepository.getByUserEmail(studentEmail).get();
        log.info("User group: {}", userGroup);

        List<StudentTestsDTO> allTests = new LinkedList<>(); // insert operation r more often compared to insert

        allTests.addAll(testRepository.getAllByGroup_Id(userGroup.getGroup().getId())
                .stream()
                .map(test -> StudentTestsDTO.builder()
                        .id(test.getId())
                        .subject(test.getSubject().getName())
                        .theme(test.getTheme())
                        .score(null)
                        .deadline(test.getDeadline())
                        .build()
                )
                .toList()
        );
        allTests.addAll(studentTestAttemptsRepository.findAllByUserId(userGroup.getUser().getId())
                .stream()
                .map(test -> StudentTestsDTO.builder()
                        .id(test.getId())
                        .subject(test.getTest().getSubject().getName())
                        .theme(test.getTest().getTheme())
                        .score(test.getScore())
                        .deadline(test.getTest().getDeadline())
                        .build())
                .toList()
        );

        log.info("Returning all user test {}", allTests.size());
        return allTests;
    }

    @Override
    public Long processStudentTest(String studentEmail, PassTestRequestDTO passTestRequest) {
        User user = userRepository.findStudentByEmail(studentEmail).get();

        log.info("Processing user {} test with params {}", studentEmail, passTestRequest);
        return studentTestAttemptsRepository.save(
                StudentTestAttempts.builder()
                        .score(passTestRequest.score())
                        .passedAt(LocalDateTime.now())
                        .test(
                                Test.builder()
                                        .id(passTestRequest.testId())
                                        .build()
                        )
                        .user(user)
                        .build()
        ).getId();
    }

    @Override
    @Transactional
    public Long createTest(String creatorEmail, TestInfoDTO testInfo) {
        User user = userRepository.findStudentByEmail(creatorEmail).get();
        log.info("CREATOR data is: {}", user);
        log.info("TEST PARAMS ARE: {}", testInfo);

        Long savedTestId = testRepository.save(
                Test.builder()
                        .theme(testInfo.getTheme())
                        .deadline(testInfo.getDeadline())
                        .createdAt(LocalDateTime.now())
                        .creator(user)
                        .subject(
                                Subject.builder()
                                        .id(testInfo.getSubjectId())
                                        .build()
                        )
                        .group(
                                Group.builder()
                                        .id(testInfo.getAssignedGroupId())
                                        .build()
                        )
                        .build()
        ).getId();

        testOptionRepository.saveAll(
                testInfo.getOptions()
                        .stream()
                        .map(testOption -> TestOption.builder()
                                .question(testOption.getQuestion())
                                .option1(testOption.getOption1())
                                .option2(testOption.getOption2())
                                .option3(testOption.getOption3())
                                .option4(testOption.getOption4())
                                .correctOption(testOption.getCorrectOption())
                                .test(
                                        Test.builder()
                                                .id(savedTestId)
                                                .build()
                                )
                                .build())
                        .toList()
        );
        log.info("New test was successfully created by {}. Test body is {}", creatorEmail, testInfo);
        return savedTestId;
    }
}
