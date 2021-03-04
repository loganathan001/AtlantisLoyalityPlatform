package com.mindtree.atlantis.loyalty.core.util;

import lombok.Data;

@Data
public class RestAPIInfo {
	
	String url;
	
	String method;
	
	String contentType;
	
	String mimeType;
	
	Integer timeoutMillisec;

}
