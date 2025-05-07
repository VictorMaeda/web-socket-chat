package projeto.chatweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@RequestMapping("/token")
@RestController
public class TokenController {

    @Value("${client.id}")
    private String clientId;
    private final String grantType = "password";

    @PostMapping("/")
    public ResponseEntity<String> token(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", Optional.ofNullable(user.clientId).orElse(clientId));
        formData.add("username", user.username);
        formData.add("password", user.password);
        formData.add("grant_type", Optional.ofNullable(user.grantType).orElse(grantType));

        var result = rt.postForEntity("http://localhost:8080/realms/youtube/protocol/openid-connect/token",
                new HttpEntity<MultiValueMap<String,String>>(formData, headers), String.class);

        return result;
    }

    public record User(String password, String clientId, String grantType, String username) {}

}
