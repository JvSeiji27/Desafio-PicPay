package com.joaovitorseiji.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joaovitorseiji.picpay.entity.Transfer;

@FeignClient(name = "notificationClient", url = "${client.notification-service-url}")
public interface NotificationClient {
	
	@PostMapping
	ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);
}
