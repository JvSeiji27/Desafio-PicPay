package com.joaovitorseiji.picpay.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.picpay.dtos.TransferDTO;
import com.joaovitorseiji.picpay.entity.Transfer;
import com.joaovitorseiji.picpay.entity.Wallet;
import com.joaovitorseiji.picpay.exception.InsufficientBalanceException;
import com.joaovitorseiji.picpay.exception.TransferNotAllowedForWalletTypeException;
import com.joaovitorseiji.picpay.exception.TransferNotAuthorizedException;
import com.joaovitorseiji.picpay.exception.WalletNotFoundException;
import com.joaovitorseiji.picpay.repositories.TransferRepository;
import com.joaovitorseiji.picpay.repositories.WalletRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public  class TransferService {

	
	@Autowired
	private TransferRepository transferRepository;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Transactional
	public Transfer transfer(@Valid TransferDTO request) {
		var sender =  walletRepository.findById(request.payer()).orElseThrow(()-> new WalletNotFoundException(request.payer()));
	
		var receiver = walletRepository.findById(request.payee()).orElseThrow(()-> new WalletNotFoundException(request.payee()));
		
		validateTransfer(request, sender);
		
		sender.debit(request.value());
		receiver.credit(request.value());
		
		var transfer = new Transfer(request.value(), sender, receiver);
		walletRepository.save(sender);
		walletRepository.save(receiver);	
		var transferResult = transferRepository.save(transfer);
		
		CompletableFuture.runAsync(()->notificationService.sendNotification(transferResult));
		
		return transferResult;
	}

	private void validateTransfer(@Valid TransferDTO request, Wallet sender) {
		if(!sender.isTypeAllowedToTransfer()) {
			throw new TransferNotAllowedForWalletTypeException();
		}
		
		if(!sender.isBalanceBiggerThan(request.value())) {
			throw new InsufficientBalanceException();
		}
			
		if(!authorizationService.isAuthorized(request)) {
			throw new TransferNotAuthorizedException();
		}
		
		
	}

}
