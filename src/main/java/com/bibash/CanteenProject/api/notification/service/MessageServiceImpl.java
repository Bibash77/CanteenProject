package com.bibash.CanteenProject.api.notification.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.api.notification.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(
        MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findOne(Long id) {
        return messageRepository.getOne(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Page<Message> findAllPageable(Object t, Pageable pageable) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> s = objectMapper.convertValue(t, Map.class);
        final NotificationSpecBuilder notificationSpecBuilder = new NotificationSpecBuilder(s);
        final Specification<Message> specification = notificationSpecBuilder.build();
        return messageRepository.findAll(specification, pageable);
    }

    @Override
    public List<Message> saveAll(List<Message> list) {
        return messageRepository.saveAll(list);
    }
}
