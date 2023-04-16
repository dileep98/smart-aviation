package com.sv.smartaviation.config.interceptor;

import com.sv.smartaviation.config.ApiConfig;
import com.sv.smartaviation.util.Constants;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import static com.sv.smartaviation.util.Constants.API_KEY;

@RequiredArgsConstructor
public class ApiKeyInterceptor implements ClientHttpRequestInterceptor {

    private final ApiConfig.SkyScannerConfig skyScannerConfig;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(API_KEY, skyScannerConfig.getKey());
        request.getHeaders().set(Constants.HOST, skyScannerConfig.getHost());

        return execution.execute(request, body);
    }
}
