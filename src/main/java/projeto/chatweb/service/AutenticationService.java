package projeto.chatweb.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projeto.chatweb.client.KeycloakClient;
import projeto.chatweb.model.User;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AutenticationService {

    private final String defaultGrantType = "password";
    private final String realm = "youtube";

    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private final Keycloak keycloak;
    private final KeycloakClient keycloakClient;

    public AutenticationService(Keycloak keycloak, KeycloakClient keycloakClient) {
        this.keycloak = keycloak;
        this.keycloakClient = keycloakClient;
    }

    public URI createUser(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(user.username());
        userRepresentation.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(user.password());
        credential.setTemporary(false);
        userRepresentation.setCredentials(List.of(credential));

        Response response = keycloak.realm(realm).users().create(userRepresentation);

        return response.getLocation();
    }

    public String requestUserToken(User user) {
        Map<String, String> form = new HashMap<>();
        form.put("username", user.username());
        form.put("password", user.password());
        form.put("grant_type", Optional.ofNullable(user.grantType()).orElse("password"));
        form.put("client_id", clientId);
        form.put("client_secret", clientSecret);

        return keycloakClient.getUserToken(form).getToken();
    }


}
