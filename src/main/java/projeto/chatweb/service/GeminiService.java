package projeto.chatweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient geminiWebClient;

    public GeminiService(WebClient geminiWebClient){
        this.geminiWebClient = geminiWebClient;
    }


    public boolean verificarSeOfensiva(String mensagem) {
        String prompt = "Essa mensagem é ofensiva? Responda apenas com 'true' ou 'false'. Mensagem: \"" + mensagem + "\"";

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        Map response = geminiWebClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // <- aqui a execução será síncrona

        try {
            var candidates = (List<?>) response.get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                var first = (Map<?, ?>) candidates.get(0);
                var content = (Map<?, ?>) first.get("content");
                var parts = (List<?>) content.get("parts");
                if (parts != null && !parts.isEmpty()) {
                    return parseBooleanStrict((String) ((Map<?, ?>) parts.get(0)).get("text"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Erro ao interpretar resposta");
    }
    public boolean parseBooleanStrict(String value) {
        if (value.contains("true")) return true;
        if (value.contains("false")) return false;
        throw new IllegalArgumentException("Valor inválido para booleano: " + value);
    }


}
