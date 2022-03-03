package com.bibash.matchella.api.notification.service;

import com.bibash.matchella.api.notification.Message;
import com.bibash.matchella.core.BaseService;

public interface MessageService extends BaseService<Message> {

     String messageGenerator(Message message);

}
