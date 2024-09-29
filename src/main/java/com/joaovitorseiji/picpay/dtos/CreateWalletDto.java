package com.joaovitorseiji.picpay.dtos;

import com.joaovitorseiji.picpay.entity.Wallet;
import com.joaovitorseiji.picpay.entity.WalletType;

//Balance e Id não são necessários, o Backend faz a gerência
public record CreateWalletDto (String fullname, String document, String email, String password, WalletType.Enum walletType) {

	public Wallet toWallet() {
			return new Wallet(this.fullname, this.document, this.email, this.password, walletType.get());
	}
}
