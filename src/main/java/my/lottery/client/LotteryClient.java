package my.lottery.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

@Slf4j
@Component
public class LotteryClient {

    private RestTemplate rest;
    private HttpHeaders headers;

    public LotteryClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/text");
        headers.add("Accept", "*/*");
    }

    public String get(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        try {
            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (HttpServerErrorException serverException){
            log.error("Server execption: [{}]", serverException.getStackTrace());
        }catch (HttpClientErrorException clientException){
            log.error("Client execption: [{}]", clientException.getStackTrace());
        }
        return null;
    }

}
