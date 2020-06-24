package com.bibash.CanteenProject.api.notification.service;

import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.core.BaseService;

public interface MessageService extends BaseService<Message> {

     String messageGenerator(Message message);

}
