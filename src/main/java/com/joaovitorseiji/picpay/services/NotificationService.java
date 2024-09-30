package com.joaovitorseiji.picpay.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.picpay.client.NotificationClient;
import com.joaovitorseiji.picpay.entity.Transfer;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationClient notificationClient;
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	public void sendNotification(Transfer transfer) {
		
		try {
			logger.info("Sending notification");
			var resp = notificationClient.sendNotification(transfer);
			
			if(resp.getStatusCode().isError()) {
				logger.error("Error while sending notification");
			}
		}catch(Exception e) {
			logger.error("Error while sending notification", e);
		}
		}
}
