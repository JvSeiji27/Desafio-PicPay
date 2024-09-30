package com.joaovitorseiji.picpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.picpay.client.AuthorizationClient;
import com.joaovitorseiji.picpay.dtos.TransferDTO;
import com.joaovitorseiji.picpay.entity.Transfer;
import com.joaovitorseiji.picpay.exception.PicPayException;

import jakarta.validation.Valid;

@Service
public class AuthorizationService {
	
	@Autowired
	private AuthorizationClient authorizationClient;
	
	public boolean isAuthorized(@Valid TransferDTO request) {
		
		var response = authorizationClient.isAuthorized();
		
		if(response.getStatusCode().isError()) {
			throw new PicPayException();
		}
		return response.getBody().authorized();
		
	}
}
