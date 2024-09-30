package com.joaovitorseiji.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.joaovitorseiji.picpay.dtos.AuthorizationResponse;

@FeignClient(name = "authorizationClient", url= "${client.authorization-service.url}")
public interface AuthorizationClient {
	
	@GetMapping
	ResponseEntity<AuthorizationResponse> isAuthorized();
}
