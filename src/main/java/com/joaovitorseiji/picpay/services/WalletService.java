package com.joaovitorseiji.picpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.picpay.dtos.CreateWalletDto;
import com.joaovitorseiji.picpay.entity.Wallet;
import com.joaovitorseiji.picpay.exception.WalletDataAlreadyExistsException;
import com.joaovitorseiji.picpay.repositories.WalletRepository;

@Service
public class WalletService {
	
	@Autowired
	WalletRepository walletRepository;
	
	
	public Wallet createWallet(CreateWalletDto dto) {
		
		var walletDb = walletRepository.findByDocumentOrEmail(dto.document(), dto.email());
		
		if(walletDb.isPresent()) {
			
			throw new WalletDataAlreadyExistsException("Cpf or CNPJ already exists");
			
		}
		return walletRepository.save(dto.toWallet());
	}
	
	
	
}
