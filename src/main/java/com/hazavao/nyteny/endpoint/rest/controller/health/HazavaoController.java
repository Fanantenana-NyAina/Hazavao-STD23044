package com.hazavao.nyteny.endpoint.rest.controller.health;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HazavaoController {

  private final String apiKey = System.getenv("OPENAI_API_KEY");

  @GetMapping
  public String health() {
    return "OK";
  }

  @GetMapping("/hazavao")
  public String getDefinition(@RequestParam String teny) {
    OpenAiService service = new OpenAiService(apiKey);

    CompletionRequest request =
        CompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .prompt("Dikateny ny '" + teny + "' : ")
            .maxTokens(50)
            .build();

    return service.createCompletion(request).getChoices().getFirst().getText();
  }
}
