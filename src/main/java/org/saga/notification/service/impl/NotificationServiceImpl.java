package org.saga.notification.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.saga.common.dto.notification.NotifyEvent;
import org.saga.notification.factory.MessageBuilderFactory;
import org.saga.notification.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationServiceImpl implements NotificationService {

  final JavaMailSender mailSender;
  final MessageBuilderFactory messageBuilderFactory;

  @Override
  public void notifyUser(NotifyEvent notifyEvent) {
    SimpleMailMessage message =  messageBuilderFactory.build(notifyEvent);
    mailSender.send(message);
  }
}
