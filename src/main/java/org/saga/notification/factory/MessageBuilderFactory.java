package org.saga.notification.factory;

import org.saga.common.dto.notification.NotifyEvent;
import org.springframework.mail.SimpleMailMessage;

public interface MessageBuilderFactory {

  SimpleMailMessage build(NotifyEvent notifyEvent);
}
