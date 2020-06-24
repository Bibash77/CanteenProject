package com.bibash.CanteenProject.web.Controller.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.OrderItem.Service.OrderService;
import com.bibash.CanteenProject.api.User.Service.UserService;
import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.api.notification.service.MessageService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;

@RestController
public class SocketController {

    private static final String API = "/socket-publisher/";
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService service;
    private final UserService userService;
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(SocketController.class);

    public SocketController(
        SimpMessagingTemplate simpMessagingTemplate,
        MessageService service, UserService userService,
        OrderService orderService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.service = service;
        this.userService = userService;
        this.orderService = orderService;
    }

    @MessageMapping("/send/message")
    public ResponseEntity<?> usingSocketMessaging(@RequestBody Message message) {
            if (ObjectUtils.isEmpty(message.getToRole()) && ObjectUtils.isEmpty(message.getToId())) {
                logger.error("Error saving message {}", message);
                return new RestResponseDto().failureModel("Error saving message");
            }
            String api = SocketController.API;
            if(!ObjectUtils.isEmpty(message.getToId())){
                api = api.concat("/"+message.getToId());
            }
           if(!ObjectUtils.isEmpty(message.getToRole())){
           api = api.concat("/"+ message.getToRole());
          }
        message.setMessage(service.messageGenerator(message));
        simpMessagingTemplate.convertAndSend(api, message);
        return new RestResponseDto().successModel(service.save(message));
    }
}
