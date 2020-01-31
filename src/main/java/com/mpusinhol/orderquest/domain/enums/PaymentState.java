package com.mpusinhol.orderquest.domain.enums;


public enum PaymentState {
	PENDING(1, "To be payed"),
	SETTLED(2, "Already payed"),
	CANCELED(3, "Payment canceled");
	
	private int id;
	private String description;
	
	private PaymentState(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentState toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for (PaymentState state : PaymentState.values()) {
			if (id.equals(state.getId())) {
				return state;
			}
		}
		
		throw new IllegalArgumentException("Payment state not found, invalid id: " + id);
	}
}
