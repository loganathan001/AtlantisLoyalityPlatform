package com.mindtree.atlantis.loyalty.core.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.mindtree.atlantis.loyalty.core.constant.RestAPI;
import com.mindtree.atlantis.loyalty.core.util.RestAPIInfo;

@EnableConfigurationProperties
@Configuration
@ConfigurationProperties("atlantis.rest")
public class RestConfig {

	private Map<String, RestAPIInfo> apis = new LinkedHashMap<String, RestAPIInfo>();

	public Optional<RestAPIInfo> getResApiInfo(RestAPI api) {
		return apis
				.entrySet()
				.stream()
				.filter(entry -> 
					entry.getKey().replace("-", "_").equalsIgnoreCase(api.name()))
				.map(entry -> entry.getValue())
				.findAny();
	}

	public Map<String, RestAPIInfo> getApis() {
		return apis;
	}

}
