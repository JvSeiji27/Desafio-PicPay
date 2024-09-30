package com.joaovitorseiji.picpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaovitorseiji.picpay.dtos.CreateWalletDto;
import com.joaovitorseiji.picpay.entity.Wallet;
import com.joaovitorseiji.picpay.services.WalletService;

import jakarta.validation.Valid;

@RestController
public class WalletController {
	@Autowired
	private WalletService walletService;
	
	@PostMapping("/wallets")
	public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto){
		var wallet = walletService.createWallet(dto);
		
		return ResponseEntity.ok(wallet);
	}
}
