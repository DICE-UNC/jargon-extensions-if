package org.irods.jargon.extensions.notification;

import java.util.List;
import java.util.Map;

import org.datacommons.model.Notification;

public interface NotificationService {

	public List<Notification> getAllNotification(String userId);

	public Map<String, Integer> getUnseenCounts(String userId);

	public Map<String, Integer> deleteNotifications(List<String> uuids);

	public Map<String, Integer> markToSeen(List<String> uuids);
	
	public List<Notification> getNotificationById(String userId, String notificationId);

}
