package com.mindtree.atlantis.loyalty.core.dto.points;

import java.util.Optional;
import java.util.stream.Stream;

public enum TransactionType {
	
	ACQUIRE("acquire"), REDEEM("redeem");
	
	private String value;
	
	private TransactionType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public Optional<TransactionType> getTransactionType(String value) {
		return Stream.of(values())
				.filter(type -> type.getValue().equalsIgnoreCase(getValue()))
				.findAny();
	}

}
