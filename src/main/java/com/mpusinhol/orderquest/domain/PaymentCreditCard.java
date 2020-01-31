package com.mpusinhol.orderquest.domain;

import javax.persistence.Entity;

import com.mpusinhol.orderquest.domain.enums.PaymentState;

@Entity
public class PaymentCreditCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer installments;
	
	public PaymentCreditCard() {}

	public PaymentCreditCard(Integer id, PaymentState state, Order order, Integer installments) {
		super(id, state, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
}
