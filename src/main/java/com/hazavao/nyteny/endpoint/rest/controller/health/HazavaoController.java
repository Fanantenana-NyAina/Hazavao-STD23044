package com.hazavao.nyteny.endpoint.rest.controller.health;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HazavaoController {

  private final String apiKey = System.getenv("OPENAI_API_KEY");

  @GetMapping("/")
  public String health() {
    return "OK";
  }

  @GetMapping("/hazavao")
  public String getDefinition(@RequestParam String teny) {
    OpenAiService service = new OpenAiService(apiKey);

    ChatMessage message = new ChatMessage("user", "Dikateny ny '" + teny + "' : ");

    ChatCompletionRequest chatRequest =
        ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(List.of(message))
            .maxTokens(50)
            .build();

    return service.createChatCompletion(chatRequest).getChoices().get(0).getMessage().getContent();
  }
}
