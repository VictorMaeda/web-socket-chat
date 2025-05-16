package projeto.chatweb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import projeto.chatweb.config.AiConfig;

import java.util.Map;

@FeignClient(name = "gemini-api", url = "#{@geminiFullUrl}")
public interface GeminiClient {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    Map gerarConteudo(@RequestBody Map<String, Object> request);

}
