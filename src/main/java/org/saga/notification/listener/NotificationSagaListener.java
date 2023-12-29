package org.saga.notification.listener;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.saga.common.dto.notification.NotifyEvent;
import org.saga.notification.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationSagaListener {

  private final NotificationService notificationService;

  @KafkaListener(
      groupId = "notification-consumer",
      topics = {"notification-saga-topic"},
      properties = {"spring.json.value.default.type=org.saga.common.dto.notification.NotifyEvent"}
  )
  public void listener(NotifyEvent notifyEvent) {
    log.info("Received notify event %s".formatted(notifyEvent));
    notificationService.notifyUser(notifyEvent);
  }

}
