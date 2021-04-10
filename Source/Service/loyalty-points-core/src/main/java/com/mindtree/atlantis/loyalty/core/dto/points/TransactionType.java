package com.mindtree.atlantis.loyalty.core.dto.points;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TransactionType {
	
	 ACQUIRED("acquired"), PURCHASED("purchased"), REDEEMD("redeemed"), ALL("all");
	
	private String value;
	
	private TransactionType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Optional<TransactionType> getTransactionType(String value) {
		return Stream.of(values())
				.filter(type -> type.getValue().equalsIgnoreCase(value))
				.findAny();
	}
	
	public static String[] resolveTransactionTypes(String[] transactionTypes) {
		Set<String> resolvedTxnTypes = new LinkedHashSet<>();
		if(transactionTypes == null || transactionTypes.length == 0) {
			resolvedTxnTypes.add(ALL.getValue());
		} else {
			resolvedTxnTypes.addAll(Arrays.asList(transactionTypes)
					.stream()
					.map(String::toLowerCase)
					.collect(Collectors.toList()));
		}
		
		if(resolvedTxnTypes.contains(ALL.getValue())) {
			return Stream.of(values())
					.filter(type -> !type.equals(TransactionType.ALL))
					.map(TransactionType::getValue)
					.toArray(size -> new String[size]);
		} else {
			return Stream.of(transactionTypes)
				.filter(type -> getTransactionType(type).isPresent())
				.toArray(size -> new String[size]);
		}
	}

}
