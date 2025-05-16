package projeto.chatweb.client;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "keycloak-api", url = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
public interface KeycloakClient {

    @PostMapping(value = "/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    AccessTokenResponse getUserToken(@RequestBody Map<String, ?> form);

    @PostMapping("/users")
    String createUser(@RequestBody Map<String, ?> form);
}
