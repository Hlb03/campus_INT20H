package com.final_proj.final_proj.service;

import com.final_proj.final_proj.dto.messages.CreateMessageDTO;
import com.final_proj.final_proj.dto.messages.MessageDTO;
import com.final_proj.final_proj.dto.messages.SendMessageResponseDTO;

import java.util.List;

public interface MessageService {

    List<MessageDTO> getAllUserMessages(String userEmail);

    SendMessageResponseDTO sendMessageIndividually(String senderEmail, CreateMessageDTO message, Long receiverId);

    SendMessageResponseDTO sendMessageToGroup(String senderEmail, CreateMessageDTO message, Long groupId);

    SendMessageResponseDTO sendMessageToFaculty(String senderEmail, CreateMessageDTO message, Long facultyId);

    SendMessageResponseDTO sendMessageToCourse(String senderEmail, CreateMessageDTO message, String courseValue);
}
