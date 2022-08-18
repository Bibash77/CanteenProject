package com.bibash.canteenproject.api.notification.service;

import com.bibash.canteenproject.api.notification.Message;
import com.bibash.canteenproject.core.BaseService;

public interface MessageService extends BaseService<Message> {

     String messageGenerator(Message message);

}
