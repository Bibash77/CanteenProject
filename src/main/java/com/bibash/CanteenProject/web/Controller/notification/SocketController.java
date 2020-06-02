package com.bibash.CanteenProject.web.Controller.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.User.Service.UserService;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.api.notification.service.MessageService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;
import com.bibash.CanteenProject.core.enums.RoleType;
import com.bibash.CanteenProject.core.enums.Status;

@RestController
public class SocketController {

    private static final String API = "/socket-publisher/";
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService service;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(SocketController.class);

    public SocketController(
        SimpMessagingTemplate simpMessagingTemplate,
        MessageService service, UserService userService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.service = service;
        this.userService = userService;
    }

    @MessageMapping("/send/message")
    public ResponseEntity<?> usingSocketMessaging(@RequestBody Message message) {
        if (message == null) {
            return new RestResponseDto().failureModel("Error saving message");
        }else {
            if (message.getToRole() != null) {
                userService.findOne(message.getFromId());
                message.setMessage(service.messageGenerator(message));
                simpMessagingTemplate.convertAndSend(
                    SocketController.API+ "/" + message.getToRole(), message);
                return new RestResponseDto().successModel(service.save(message));
            } else {
                logger.error("Error saving message {}", message);
                return new RestResponseDto().failureModel("Error saving message");
            }
        }
    }
}
