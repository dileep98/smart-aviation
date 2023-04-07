package com.sv.smartaviation.config.interceptor;

import com.sv.smartaviation.config.AirlabsConfig;
import java.io.IOException;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import static com.sv.smartaviation.util.Constants.API_KEY;

@RequiredArgsConstructor
public class AirLabsApiKeyInterceptor implements ClientHttpRequestInterceptor {

    private final AirlabsConfig airlabsConfig;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        URI uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam(API_KEY, airlabsConfig.getKey())
                .build().toUri();

        HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };
        return execution.execute(modifiedRequest, body);
    }
}
