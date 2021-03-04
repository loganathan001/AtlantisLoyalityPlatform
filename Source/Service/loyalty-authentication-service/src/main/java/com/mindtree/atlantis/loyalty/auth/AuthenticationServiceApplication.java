package com.mindtree.atlantis.loyalty.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mindtree.atlantis.loyalty.core.config.CoreConfig;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisExceptionHandler;
import com.mindtree.atlantis.loyalty.core.util.RestConfig;
import com.mindtree.atlantis.loyalty.core.util.RestUtil;

@SpringBootApplication
@Import({RestUtil.class, CoreConfig.class, RestConfig.class, AtlantisExceptionHandler.class})
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
