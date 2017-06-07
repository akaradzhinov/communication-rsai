package bg.sofia.tu.rsai.communication.rest;

import bg.sofia.tu.rsai.communication.Message;
import bg.sofia.tu.rsai.communication.error.CommunicationErrorHandler;
import bg.sofia.tu.rsai.communication.response.CommunicationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 07/06/2017 @ 23:05.
 */
@Service
public class CommunicationRestTemplate {

    @Value("${communication.retry.attempts}")
    private int retryAttempts;

    private final RestTemplate restTemplate;

    public CommunicationRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        restTemplate.setErrorHandler(new CommunicationErrorHandler());
    }

    public CommunicationResponse post(String url, Message message) {
        HttpEntity<Message> entity = new HttpEntity<>(message);
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if(response.getStatusCode() != HttpStatus.OK) {
            System.out.println("POST call to " + url + " failed with body: " + message.toString());
            for (int i = 0; i < retryAttempts; i++) {
                System.out.println("Retrying post request with " + (retryAttempts - i) + " attempts left...");
                response = this.restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    break;
                }
            }
        }
        return new CommunicationResponse(response.getStatusCode(), response.getBody());
    }
}
