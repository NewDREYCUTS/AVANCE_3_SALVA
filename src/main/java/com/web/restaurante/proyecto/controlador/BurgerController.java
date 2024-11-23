package com.web.restaurante.proyecto.controlador;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/burger")
public class BurgerController {

    private final WebClient webClient;

    public BurgerController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    @PostMapping("/preview")
    public ResponseEntity<String> getBurgerPreview(@RequestBody BurgerRequest burgerRequest) {
        // Generar el prompt basado en los ingredientes proporcionados
        String prompt = generatePrompt(burgerRequest.getIngredients());

        String apiKey = "k-w3TnFSB7IcyHNePOIlQTT3BlbkFJfURY22AV010cTznLAriR";
        // Hacer la solicitud a la API de OpenAI
        String response = webClient.post()
                .uri("/images/generations")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(new DalleRequest(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("API Response: " + response); // Imprimir la respuesta para depuración

        // Extraer la URL de la imagen de la respuesta JSON
        String imageUrl = extractImageUrl(response);

        return ResponseEntity.ok(imageUrl);
    }

    private String generatePrompt(String[] ingredients) {
        // Generar una descripción del texto a partir de los ingredientes
        StringBuilder prompt = new StringBuilder("Crear una imagen de una hamburguesa realista con ");
        for (String ingredient : ingredients) {
            prompt.append(ingredient).append(", ");
        }
        return prompt.toString().trim();
    }

    private String extractImageUrl(String response) {
        try {
            // Parsear la respuesta JSON para extraer la URL de la imagen
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode urlNode = rootNode.path("data").get(0).path("url");
            return urlNode.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static class BurgerRequest {
        private String[] ingredients;

        public String[] getIngredients() {
            return ingredients;
        }

        public void setIngredients(String[] ingredients) {
            this.ingredients = ingredients;
        }

        @Override
        public String toString() {
            return "BurgerRequest{" +
                    "ingredients=" + Arrays.toString(ingredients) +
                    '}';
        }
    }

    static class DalleRequest {
        private final String prompt;

        public DalleRequest(String prompt) {
            this.prompt = prompt;
        }

        public String getPrompt() {
            return prompt;
        }
    }
}