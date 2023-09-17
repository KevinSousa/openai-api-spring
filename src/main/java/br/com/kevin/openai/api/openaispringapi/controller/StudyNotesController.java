package br.com.kevin.openai.api.openaispringapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kevin.openai.api.openaispringapi.service.StudyNotesServiceChatGPT;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("study-notes")
public class StudyNotesController {

    private StudyNotesServiceChatGPT studyNotesServiceChatGPT;

    public StudyNotesController(StudyNotesServiceChatGPT studyNotesServiceChatGPT) {
        this.studyNotesServiceChatGPT = studyNotesServiceChatGPT;
    }

    @PostMapping
    public Mono<String> createStudyNotes(@RequestBody String topic) {
        return studyNotesServiceChatGPT.createStudyNotes(topic).map(
                response -> response.choices().get(0).text());
    }
}
