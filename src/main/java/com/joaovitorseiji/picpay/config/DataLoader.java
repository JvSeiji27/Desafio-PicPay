package com.joaovitorseiji.picpay.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaovitorseiji.picpay.entity.WalletType;
import com.joaovitorseiji.picpay.repositories.WalletTypeRepository;

@Configuration
public class DataLoader implements CommandLineRunner{ //Vai executar assim que o projeto inicializar

	@Autowired
	private WalletTypeRepository walletTypeRepository;
	
	@Override //O que eu quero qeu ele faça quando incializar?
	public void run(String... args) throws Exception {
		Arrays.stream(WalletType.Enum.values())
		.forEach(walletType -> walletTypeRepository.save(walletType.get()));
		
	}
	
}
