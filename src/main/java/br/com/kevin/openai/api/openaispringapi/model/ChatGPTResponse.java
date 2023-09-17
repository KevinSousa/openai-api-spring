package br.com.kevin.openai.api.openaispringapi.model;

import java.util.List;

public record ChatGPTResponse(List<Choices> choices) {
    
}
