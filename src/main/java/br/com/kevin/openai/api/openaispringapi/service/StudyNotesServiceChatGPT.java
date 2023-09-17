package br.com.kevin.openai.api.openaispringapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.kevin.openai.api.openaispringapi.model.ChatGPTRequest;
import br.com.kevin.openai.api.openaispringapi.model.ChatGPTResponse;
import reactor.core.publisher.Mono;

@Service
public class StudyNotesServiceChatGPT {

    private WebClient webClient;

    public StudyNotesServiceChatGPT(WebClient.Builder webClient, @Value("${openai.api.key}") String apiKey) {
        this.webClient = webClient
                .baseUrl("https://api.openai.com/v1/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", String.format("Bearer %s", apiKey))
                .build();
    }

    public Mono<ChatGPTResponse> createStudyNotes(String topic) {
        ChatGPTRequest request = createStudyRequest(topic);
        return webClient.post().bodyValue(request).retrieve().bodyToMono(ChatGPTResponse.class);
    }

    private ChatGPTRequest createStudyRequest(String topic) {
        String question = "Quais são os pontos chave que devo estudar sobre o seguinte assunto: " + topic;
        return new ChatGPTRequest("text-davinci-003", question, 0.3, 2000, 1.0, 0.0, 0.0);
    }

}
