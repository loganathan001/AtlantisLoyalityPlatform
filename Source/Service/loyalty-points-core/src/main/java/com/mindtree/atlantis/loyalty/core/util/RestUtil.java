package com.mindtree.atlantis.loyalty.core.util;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClientException;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;
import com.mindtree.atlantis.loyalty.core.constant.RestAPI;
import com.mindtree.atlantis.loyalty.core.exception.RestException;

import reactor.core.publisher.Mono;

@Component
public class RestUtil {

	@Autowired
	private RestConfig restConfig;

	/**
	 * Do post.
	 *
	 * @param api         the api
	 * @param requestBody the request body. For MultiPart Form Data / Form URL
	 *                    Encoded data the requestBody should be instance of
	 *                    MultiValueMap, otherwise, it can be any object
	 * @return the response wrapper<? extends object>
	 * @throws RestException the rest exception
	 */
	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> doHttpRequest(RestAPI api, Map<String, ?> uriVariables, Object requestBody, Class<T> responseType) throws RestException {
		Optional<RestAPIInfo> resApiInfoOpt = restConfig.getResApiInfo(api);
		if (resApiInfoOpt.isEmpty()) {
			throw new RestException(AtlantisErrorConstants.MISSING_CONFIGURATION,
					"atlantis.rest." + api.name().toLowerCase().replace("_", "-"));
		}

		RestAPIInfo restAPIInfo = resApiInfoOpt.get();

		Builder webClientBuilder = WebClient.builder();
		WebClient webClient = webClientBuilder.build();
		RequestBodyUriSpec requestBodyUriSpec = webClient.method(HttpMethod.valueOf(restAPIInfo.getMethod()));
		
		requestBodyUriSpec.uri(restAPIInfo.getUrl(), uriVariables == null ? Map.of() : uriVariables);

		if (restAPIInfo.getContentType() != null) {
			requestBodyUriSpec.contentType(MediaType.valueOf(restAPIInfo.getContentType()));
		}

		if (restAPIInfo.getMimeType() != null) {
			requestBodyUriSpec.accept(MediaType.valueOf(restAPIInfo.getContentType()));
		}

		if (requestBody != null) {
			// For MultiPart Form Data / Form URL Encoded data the requestBody should be
			// instance of MultiValueMap, otherwise, it can be any object
			requestBodyUriSpec.syncBody(requestBody);
		}

		Mono<ClientResponse> mono = requestBodyUriSpec.exchange();

		try {
			T response;
			if (responseType != null) {
				Object responseObj;
				if (restAPIInfo.getTimeoutMillisec() != null) {
					responseObj = mono.block(Duration.ofMillis(restAPIInfo.getTimeoutMillisec()));
				} else {
					responseObj = mono.block();
				}
				if (ClientResponse.class.isAssignableFrom(responseType)) {
					response = (T) responseObj;
				} else {
					ClientResponse clientResponse = (ClientResponse) responseObj;
					response = clientResponse.bodyToMono(responseType).block();
				}

				return new ResponseEntity<>(response, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (WebClientException e) {
			throw new RestException(AtlantisErrorConstants.REST_ERROR, e);
		}
	}

}
