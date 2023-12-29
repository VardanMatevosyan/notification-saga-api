package org.saga.notification.factory.impl;

import static org.saga.common.enums.OrderStatus.ORDER_COMPLETED;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.saga.common.dto.notification.NotifyEvent;
import org.saga.notification.factory.MessageBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageBuilderFactoryImpl implements MessageBuilderFactory {

  @Value(value = "${email.from}")
  String emailFrom;

  @Override
  public SimpleMailMessage build(NotifyEvent notifyEvent) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailFrom);
    message.setTo(notifyEvent.getCustomerEmail());
    message.setSubject(buildSubject(notifyEvent));
    message.setText(buildMessageBody(notifyEvent));
    return message;
  }

  private String buildMessageBody(NotifyEvent notifyEvent) {
    return notifyEvent.getOrderStatus().equals(ORDER_COMPLETED)
        ? "Order %s has be successfully processed"
        : "Can't processed order. Please try again";
  }

  private String buildSubject(NotifyEvent notifyEvent) {
    return "No reply. Order %d".formatted(notifyEvent.getOrderId());
  }

}
