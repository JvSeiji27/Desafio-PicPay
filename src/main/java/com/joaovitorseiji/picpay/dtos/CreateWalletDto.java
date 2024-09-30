package com.joaovitorseiji.picpay.dtos;

import com.joaovitorseiji.picpay.entity.Wallet;
import com.joaovitorseiji.picpay.entity.WalletType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Balance e Id não são necessários, o Backend faz a gerência
public record CreateWalletDto (@NotBlank String fullname, 
							   @NotBlank String document, 
							   @NotBlank String email, 
							   @NotBlank String password, 
							   @NotNull WalletType.Enum walletType) {

	public Wallet toWallet() {
			return new Wallet(this.fullname, this.document, this.email, this.password, walletType.get());
	}
}
