package com.final_proj.final_proj.controller;

import com.final_proj.final_proj.dto.messages.CreateMessageDTO;
import com.final_proj.final_proj.dto.messages.MessageDTO;
import com.final_proj.final_proj.dto.messages.SendMessageResponseDTO;
import com.final_proj.final_proj.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/messages/")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDTO> getAllMessages(Principal principal) {
        return messageService.getAllUserMessages(principal.getName());
    }

    @PostMapping("send/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public SendMessageResponseDTO sendMessageToStudent(Principal principal,
                                                       @PathVariable Long studentId,
                                                       @RequestBody CreateMessageDTO message) {
        return messageService.sendMessageIndividually(principal.getName(), message, studentId);
    }

    @PostMapping("send/group/{groupId}")
    public SendMessageResponseDTO sendMessageToGroup(Principal principal,
                                                     @PathVariable Long groupId,
                                                     @RequestBody CreateMessageDTO message) {
        return messageService.sendMessageToGroup(principal.getName(), message, groupId);
    }

    @PostMapping("send/faculty/{facultyId}")
    public SendMessageResponseDTO sendMessageToFaculty(Principal principal,
                                                       @PathVariable Long facultyId,
                                                       @RequestBody CreateMessageDTO message) {
        return messageService.sendMessageToFaculty(principal.getName(), message, facultyId);
    }

    @PostMapping("send/course/{courseValue}")
    public SendMessageResponseDTO sendMessageToFaculty(Principal principal,
                                                       @PathVariable String courseValue,
                                                       @RequestBody CreateMessageDTO message) {
        return messageService.sendMessageToCourse(principal.getName(), message, courseValue);
    }
}
