package com.final_proj.final_proj.service.implementation;

import com.final_proj.final_proj.dto.messages.CreateMessageDTO;
import com.final_proj.final_proj.dto.messages.MessageDTO;
import com.final_proj.final_proj.dto.messages.SendMessageResponseDTO;
import com.final_proj.final_proj.entity.*;
import com.final_proj.final_proj.entity.message_types.CourseMessage;
import com.final_proj.final_proj.entity.message_types.FacultyMessage;
import com.final_proj.final_proj.entity.message_types.GroupMessage;
import com.final_proj.final_proj.entity.message_types.IndividualMessage;
import com.final_proj.final_proj.mapper.MessageMapper;
import com.final_proj.final_proj.repository.UserGroupRepository;
import com.final_proj.final_proj.repository.UserRepository;
import com.final_proj.final_proj.repository.message_repositories.CourseMessageRepository;
import com.final_proj.final_proj.repository.message_repositories.FacultyMessageRepository;
import com.final_proj.final_proj.repository.message_repositories.GroupMessageRepository;
import com.final_proj.final_proj.repository.MessageRepository;
import com.final_proj.final_proj.repository.message_repositories.IndividualMessageRepository;
import com.final_proj.final_proj.service.MessageService;
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
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final GroupMessageRepository groupMessageRepository;
    private final FacultyMessageRepository facultyMessageRepository;
    private final CourseMessageRepository courseMessageRepository;
    private final IndividualMessageRepository individualMessageRepository;
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    private final MessageMapper messageMapper;


    @Override
    public List<MessageDTO> getAllUserMessages(String userEmail) {
        UsersGroup userGroup = userGroupRepository.getByUserId(
                userRepository.findStudentByEmail(userEmail).get().getId()
        ).get();

        List<MessageDTO> userMessages = new LinkedList<>(); // write operation r more common than read

        userMessages.addAll(individualMessageRepository.getAllByReceiverId(userGroup.getUser().getId())
                .stream()
                .map(msg -> new MessageDTO(
                        msg.getId(), msg.getMessage().getTitle(), msg.getMessage().getMessageBody(),
                        msg.getMessage().getSendDate(), msg.getMessage().getSender().getEmail(), "Individual")
                ).toList()
        );
        userMessages.addAll(groupMessageRepository.getAllByGroup_Id(userGroup.getUser().getId())
                .stream()
                .map(msg ->
                        new MessageDTO(
                                msg.getId(), msg.getMessage().getTitle(), msg.getMessage().getMessageBody(),
                                msg.getMessage().getSendDate(), msg.getMessage().getSender().getEmail(), "Group")
                ).toList()
        );
        userMessages.addAll(facultyMessageRepository.getAllByFaculty_Id(userGroup.getGroup().getFaculty().getId())
                .stream()
                .map(msg -> new MessageDTO(
                        msg.getId(), msg.getMessage().getTitle(), msg.getMessage().getMessageBody(),
                        msg.getMessage().getSendDate(), msg.getMessage().getSender().getEmail(), "Faculty"))
                .toList()
        );
        userMessages.addAll(courseMessageRepository.getAllByCourse(userGroup.getCourse())
                .stream()
                .map(msg -> new MessageDTO(
                        msg.getId(), msg.getMessage().getTitle(), msg.getMessage().getMessageBody(),
                        msg.getMessage().getSendDate(), msg.getMessage().getSender().getEmail(), "Course"))
                .toList()
        );
        log.info("Returning all message to user with email {}. Total size is {}", userEmail, userMessages.size());
        return userMessages;
    }

    @Override
    public SendMessageResponseDTO sendMessageIndividually(String senderEmail, CreateMessageDTO message, Long receiverId) {
        Message msg = saveMessage(message, senderEmail);
        individualMessageRepository.save(
                IndividualMessage.builder()
                        .message(msg)
                        .receiver(User.builder().id(receiverId).build())
                        .build()
        );
        log.info("Message was successfully send from {} to the user with id {}", senderEmail, receiverId);
        return formSendResponse("Message was send to the user with id %s".formatted(receiverId));
    }

    @Override
    @Transactional
    public SendMessageResponseDTO sendMessageToGroup(String senderEmail, CreateMessageDTO message, Long groupId) {
        Message msg = saveMessage(message, senderEmail);
        groupMessageRepository.save(
                GroupMessage.builder()
                        .message(msg)
                        .group(Group.builder().id(groupId).build())
                        .build()
        );
        log.info("Message was successfully send from {} to group with id {}", senderEmail, groupId);
        return formSendResponse("Message was send to group with id %s".formatted(groupId));
    }

    @Override
    @Transactional
    public SendMessageResponseDTO sendMessageToFaculty(String senderEmail, CreateMessageDTO message, Long facultyId) {
        Message msg = saveMessage(message, senderEmail);
        facultyMessageRepository.save(
                FacultyMessage.builder()
                        .message(msg)
                        .faculty(Faculty.builder().id(facultyId).build())
                        .build()
        );
        log.info("Message was successfully send from {} to faculty with id {}", senderEmail, facultyId);
        return formSendResponse("Message was send to faculty with id %s".formatted(facultyId));
    }

    @Override
    @Transactional
    public SendMessageResponseDTO sendMessageToCourse(String senderEmail, CreateMessageDTO message, String courseValue) {
        Message msg = saveMessage(message, senderEmail);
        courseMessageRepository.save(
                CourseMessage.builder()
                        .message(msg)
                        .course(Course.valueOf(courseValue))
                        .build()
        );
        log.info("Message was successfully send from {}th to {} course", senderEmail, courseValue);
        return formSendResponse("Message was send to the %sth course".formatted(courseValue));
    }

    private Message saveMessage(CreateMessageDTO message, String senderEmail) {
        return messageRepository.save(
                messageMapper.messageFromDto(message, userRepository.findStudentByEmail(senderEmail).get())
        );
    }

    private SendMessageResponseDTO formSendResponse(String message) {
        return new SendMessageResponseDTO(LocalDateTime.now(), message);
    }
}
