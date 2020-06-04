package com.bibash.CanteenProject.api.notification.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bibash.CanteenProject.api.User.Service.UserService;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.User.repository.UserRepository;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.api.notification.repository.MessageRepository;
import com.bibash.CanteenProject.core.enums.Status;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final WalletService walletService;

    public MessageServiceImpl(
        MessageRepository messageRepository,
        UserService userService,
        WalletService walletService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.walletService = walletService;
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
        s.values().removeIf(Objects::isNull);
        final NotificationSpecBuilder notificationSpecBuilder = new NotificationSpecBuilder(s);
        final Specification<Message> specification = notificationSpecBuilder.build();
            return messageRepository.findAll(specification, pageable);
    }

    @Override
    public List<Message> saveAll(List<Message> list) {
        return messageRepository.saveAll(list);
    }

    public String messageGenerator(Message message){
        User user = userService.findById(message.getFromId());
        message.setStatus(Status.ACTIVE);

        switch (message.getTransactionType()){
            case ORDER:
                return "Order Code:"+message.getOrderCode()+". "+user.getUserName() + " has ordered "+ message.getQuantity()+ " unit " + message.getItemName() +". Rs "+ message.getTransactionAmount() +" is deducted.";
            case TOPUP:
                return "Admin has TopUp Rs. "+message.getTransactionAmount() +"in " + user.getUserName() + " Account";
            case CANCEL:
                if (message.getItemName() == null) {
                    return
                        " Rs." + message.getTransactionAmount() + " is added in" + user
                            .getUserName() + " account by Admin";
                } else {
                    return
                        user.getUserName() + " has cancel order of " + message.getItemName() + ". Rs"
                            + message.getTransactionAmount() + " is added in account.";
                }
        }
        return "";
    }
}
