package com.joaovitorseiji.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaovitorseiji.picpay.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

}
