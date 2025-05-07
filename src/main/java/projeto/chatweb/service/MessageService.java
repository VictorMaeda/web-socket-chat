package projeto.chatweb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

public void sendMessage(Message message) {
    if (!geminiService.verificarSeOfensiva(message.getMessage())){
        messagingTemplate.convertAndSend(nomeSala+"/messages", message);
    }else{
        throw new BusinessException("Mensagem inválida");
    }
}
private boolean validateMessage(String message) {
        return true;
}
}
