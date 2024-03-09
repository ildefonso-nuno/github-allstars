package org.nildefonso.githuballstars.service.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GitHubClient {

    @Value("${github.api.token}")
    private String githubApiToken;

    @Bean
    public WebClient.Builder webClientBuilder() {
        // Configure a global exchange strategy to increase the available memory
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // 2 MB
                .build();

        return WebClient.builder()
                .exchangeStrategies(strategies)
                .defaultHeader("Accept", "application/vnd.github.v3+json")
                .defaultHeader("Authorization", "Bearer " + githubApiToken);
    }

    @Bean
    public WebClient allStarsClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.github.com")
                .build();
    }
}
