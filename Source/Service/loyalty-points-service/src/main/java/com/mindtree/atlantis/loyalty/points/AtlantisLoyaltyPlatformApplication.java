package com.mindtree.atlantis.loyalty.points;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mindtree.atlantis.loyalty.core.exception.AtlantisExceptionHandler;

@SpringBootApplication
@Import({ AtlantisExceptionHandler.class })
public class AtlantisLoyaltyPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlantisLoyaltyPlatformApplication.class, args);
	}

}
