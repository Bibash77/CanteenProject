package com.bibash.canteenproject.web.controller.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.canteenproject.api.notification.Message;
import com.bibash.canteenproject.api.notification.service.MessageService;
import com.bibash.canteenproject.core.Dto.RestResponseDto;
import com.bibash.canteenproject.core.PaginationUtils;

@RestController
@RequestMapping(NotificationController.API)
public class NotificationController {

    static final String API = "/v1/notification";
    private final MessageService service;
    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    public NotificationController(
        MessageService messageService
    ) {
        this.service = messageService;
    }

    @PostMapping
    public ResponseEntity<?> saveNotification(@RequestBody Message message) {
        Message savedMessage = service.save(message);
        if (savedMessage == null) {
            logger.error("Error saving notification.");
            return new RestResponseDto().failureModel("Error saving notification");
        } else {
            return new RestResponseDto().successModel(savedMessage);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new RestResponseDto().successModel(service.findOne(id));
    }

    @PostMapping(path = "/list")
    public ResponseEntity<?> getPageable(@RequestBody Object searchDto,
        @RequestParam("page") int page, @RequestParam("size") int size) {
        return new RestResponseDto().successModel(
            service.findAllPageable(searchDto, PaginationUtils.pageable(page, size)));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        return new RestResponseDto().successModel(service.findAll());
    }
}
