package com.joaovitorseiji.picpay.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaovitorseiji.picpay.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer,UUID>{

}
