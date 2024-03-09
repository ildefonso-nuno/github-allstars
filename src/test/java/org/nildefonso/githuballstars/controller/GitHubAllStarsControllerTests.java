package org.nildefonso.githuballstars.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.nildefonso.githuballstars.dto.AllStarDto;
import org.nildefonso.githuballstars.service.impl.GitHubAllStarsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@WebFluxTest(GitHubAllStarsController.class)
@Import(GitHubAllStarsImpl.class)
public class GitHubAllStarsControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GitHubAllStarsImpl gitHubAllStarsImpl;

    @BeforeEach
    void setUp() {
        List<AllStarDto> allStarsList = Collections.singletonList(
                new AllStarDto(1L, "Test Repo", "Description", "https://github.com/test", 100L, "Java", "2023-01-01")
        );
        Mono<List<AllStarDto>> allStarsMono = Mono.just(allStarsList);

        Mockito.when(gitHubAllStarsImpl.getAllStarsIncompleteResults(Mockito.any(LocalDate.class), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(allStarsMono);

        Mockito.when(gitHubAllStarsImpl.getAllStarsCompleteResults(Mockito.any(LocalDate.class), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(allStarsMono);
    }

    @Test
    public void getAllStarsIncompleteResults_ReturnsListOfStars() {
        webTestClient.get().uri("/api/github/getallstars/incompleteresults?date=2021-01-01&per_page=10&language=Java")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Test Repo")
                .jsonPath("$[0].stargazers_count").isEqualTo(100);
    }

    @Test
    public void getAllStarsCompleteResults_ReturnsListOfStars() {
        webTestClient.get().uri("/api/github/getallstars/completeresults?date=2021-01-01&per_page=10&language=Java")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Test Repo")
                .jsonPath("$[0].stargazers_count").isEqualTo(100);
    }
}
