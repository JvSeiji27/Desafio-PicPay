package com.joaovitorseiji.picpay.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transfer")
public class Transfer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id; //Mais seguro!
	
	@ManyToOne
	@JoinColumn(name = "wallet_sender_id")
	private Wallet sender;
	
	@ManyToOne
	@JoinColumn(name = "wallet_receiver_id" )
	private Wallet receiver;
	
	@Column(name = "value")
	private BigDecimal value;
	
	public Transfer() {
		
	}
	
	public Transfer(BigDecimal value, Wallet sender, Wallet receiver) {
		this.value = value;
		this.sender = sender;
		this.receiver = receiver;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Wallet getSender() {
		return sender;
	}

	public void setSender(Wallet sender) {
		this.sender = sender;
	}

	public Wallet getReceiver() {
		return receiver;
	}

	public void setReceiver(Wallet receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
