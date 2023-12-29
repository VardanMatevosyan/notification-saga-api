package org.saga.notification.service;

import org.saga.common.dto.notification.NotifyEvent;

public interface NotificationService {

  void notifyUser(NotifyEvent notifyEvent);
}
