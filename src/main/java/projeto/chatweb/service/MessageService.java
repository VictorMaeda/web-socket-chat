package projeto.chatweb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import projeto.chatweb.error.BusinessException;
import projeto.chatweb.model.Message;

@Service
public class MessageService {
    @Value("${room.name}")
    private String nomeSala;

    private final SimpMessagingTemplate messagingTemplate;
    private final GeminiService geminiService;
    public MessageService(SimpMessagingTemplate messagingTemplate, GeminiService geminiService) {
        this.messagingTemplate = messagingTemplate;
        this.geminiService = geminiService;
    }


public void sendMessage(String text) {
    if (!geminiService.verificarSeOfensiva(text)){
        messagingTemplate.convertAndSend(nomeSala+"/messages",  new Message(text, ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getToken().getClaimAsString("preferred_username")));
    }else{
        throw new BusinessException("Mensagem inválida");
    }
}
private boolean validateMessage(String message) {
        return true;
}
}
