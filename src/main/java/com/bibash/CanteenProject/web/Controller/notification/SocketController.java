package com.bibash.CanteenProject.web.Controller.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.api.notification.service.MessageService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;

@RestController
public class SocketController {

    private static final String API = "/socket-publisher/";
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService service;
    private final Logger logger = LoggerFactory.getLogger(SocketController.class);

    public SocketController(
        SimpMessagingTemplate simpMessagingTemplate,
        MessageService service) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.service = service;
    }

    @MessageMapping("/send/message")
    public ResponseEntity<?> usingSocketMessaging(@RequestBody Message message) {
        if (message == null) {
            return new RestResponseDto().failureModel("Error saving message");
        }else {
            if (message.getToId() != null && message.getToRole() != null) {
                simpMessagingTemplate.convertAndSend(
                    SocketController.API + message.getToId() + "/" + message.getToRole(), message);
                return new RestResponseDto().successModel(service.save(message));
            } else {
                logger.error("Error saving message {}", message);
                return new RestResponseDto().failureModel("Error saving message");
            }
        }
    }
}