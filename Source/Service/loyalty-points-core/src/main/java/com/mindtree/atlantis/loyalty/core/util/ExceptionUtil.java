package com.mindtree.atlantis.loyalty.core.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class ExceptionUtil {

	private ExceptionUtil() {
	}
	
	public static String getStackTraceAsString(Exception ex) {
		return Arrays.stream(ex.getStackTrace()).map(e -> e.toString()).collect(Collectors.joining("\n"));
	}

}
