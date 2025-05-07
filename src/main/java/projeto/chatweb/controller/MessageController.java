package projeto.chatweb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.chatweb.model.Message;
import projeto.chatweb.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public String sendMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
        return "Message sent successfully";
    }
}
