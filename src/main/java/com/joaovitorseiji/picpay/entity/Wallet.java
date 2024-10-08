package com.joaovitorseiji.picpay.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_wallet")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name")
	private String fullname;
	
	@Column(name = "document", unique = true)
	private String document;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "balance")
	private BigDecimal balance = BigDecimal.ZERO;
	
	@ManyToOne //Varios Wallets types estarão relacionados a uma carteira
	@JoinColumn(name = "wallet_type_id") //Será a chave estrnageira
	private WalletType walletType;

	public Wallet() {
		
	}
	
	

	public Wallet(String fullname, String document, String email, String password, WalletType walletType) {
		super();
		this.fullname = fullname;
		this.document = document;
		this.email = email;
		this.password = password;
		this.walletType = walletType;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}



	@Override
	public int hashCode() {
		return Objects.hash(balance, document, email, fullname, id, password, walletType);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(document, other.document)
				&& Objects.equals(email, other.email) && Objects.equals(fullname, other.fullname)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(walletType, other.walletType);
	}



	public boolean isTypeAllowedToTransfer() {
	    System.out.println("Wallet type: " + walletType);
	    if(walletType.equals(WalletType.Enum.USER.get())) {
	        return true;
	    }
	    return false;
	}
	
	public boolean isBalanceBiggerThan(BigDecimal value) {
		if(value.compareTo(balance) <= 0) {
			return true;
		}
		return false;
	}



	public void debit(BigDecimal value) {
		this.setBalance(balance.subtract(value));
		
	}
	
	public void credit(BigDecimal value) {
		this.setBalance(balance.add(value));		
	}

}
