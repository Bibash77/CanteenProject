package com.bibash.CanteenProject.web.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;

@Controller
public class NotificationController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponseEntity<?> notify(String message){

return new RestResponseDto().successModel(message);
    }

}
