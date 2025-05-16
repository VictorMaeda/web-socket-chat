package projeto.chatweb.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Bean
    public String geminiFullUrl() {
        // Monta a URL completa com o endpoint + chave
        return apiUrl + ":generateContent?key=" + apiKey;
    }


//    @Value("${gemini.api.url}")
//    private String apiUrl;

//    @Bean
//    @Qualifier("geminiWebClient")
//    public WebClient geminiWebClient() {
//        return WebClient.builder()
//                .baseUrl(apiUrl + ":generateContent?key=" + apiKey)
//                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                .build();
//    }
}
