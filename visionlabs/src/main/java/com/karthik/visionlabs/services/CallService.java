package com.karthik.visionlabs.services;

import com.karthik.visionlabs.dtos.createStreamRequestDTO;
import com.karthik.visionlabs.dtos.createStreamResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
public class CallService {

    @Value("${zenstream.api.key}")
    private String apiKey;

    @Value("${zenstream.api.base-url}")
    private String baseUrl;


    private final RestClient restClient = new RestClient() {
        @Override
        public RequestHeadersUriSpec<?> get() {
            return null;
        }

        @Override
        public RequestHeadersUriSpec<?> head() {
            return null;
        }

        @Override
        public RequestBodyUriSpec post() {
            return null;
        }

        @Override
        public RequestBodyUriSpec put() {
            return null;
        }

        @Override
        public RequestBodyUriSpec patch() {
            return null;
        }

        @Override
        public RequestHeadersUriSpec<?> delete() {
            return null;
        }

        @Override
        public RequestHeadersUriSpec<?> options() {
            return null;
        }

        @Override
        public RequestBodyUriSpec method(HttpMethod method) {
            return null;
        }

        @Override
        public Builder mutate() {
            return null;
        }
    };


    public createStreamResponseDTO createStream(String title, String instructor,
                                                LocalDateTime scheduledTime,int duration){

        String url = baseUrl+"/streams";
        HttpHeaders headers= new HttpHeaders();
        headers.set("Authorization","Bearer" + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        createStreamRequestDTO reqBody = new createStreamRequestDTO();
        reqBody.setTitle(title);
        reqBody.setInstructor(instructor);
        reqBody.setScheduledTime(scheduledTime.toString());
        reqBody.setDurationMinutes(duration);

        HttpEntity<createStreamRequestDTO> request = new HttpEntity<>(reqBody,headers);

        ResponseEntity<createStreamResponseDTO> response = restClient.post()
                .uri(url)
                .body(request)
                .retrieve()
                .toEntity(createStreamResponseDTO.class);

    }
}
