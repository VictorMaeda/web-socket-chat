package projeto.chatweb.controller;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.chatweb.model.User;
import projeto.chatweb.service.AutenticationService;

@RestController
@RequestMapping("/user")
public class AutenticationController {
    private final AutenticationService autenticationService;
    public AutenticationController(AutenticationService autenticationService) {
        this.autenticationService = autenticationService;
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuário(@RequestBody User user){
        return ResponseEntity.ok(autenticationService.createUser(user));
    }
    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody User user){
        return ResponseEntity.ok(autenticationService.requestUserToken(user));
    }
}
