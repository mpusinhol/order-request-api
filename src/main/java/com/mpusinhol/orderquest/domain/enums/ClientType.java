package com.mpusinhol.orderquest.domain.enums;

public enum ClientType {
	INDIVIDUALPERSON(1, "Individual Person"),
	LEGALPERSON(2, "Legal Person");
	
	private int id;
	private String description;
	
	private ClientType(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for (ClientType type : ClientType.values()) {
			if (id.equals(type.getId())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid id" + id);
	}
}
