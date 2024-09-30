package com.joaovitorseiji.picpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaovitorseiji.picpay.dtos.TransferDTO;
import com.joaovitorseiji.picpay.entity.Transfer;
import com.joaovitorseiji.picpay.services.TransferService;

import jakarta.validation.Valid;

@RestController
public class TransferController {
	
	@Autowired
	private TransferService transferService;
	
	@PostMapping("/transfer")
	public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO request){
		var resp = transferService.transfer(request);
		
		return ResponseEntity.ok(resp);
	}
}
