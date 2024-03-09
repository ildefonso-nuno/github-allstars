package org.nildefonso.githuballstars.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.nildefonso.githuballstars.dto.AllStarDto;
import org.nildefonso.githuballstars.mapper.AllStarMapper;
import org.nildefonso.githuballstars.model.GitHubSearchRepository;
import org.nildefonso.githuballstars.model.GitHubSearchResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

class GitHubAllStarsImplTests {
    private WebClient webClientMock;
    private AllStarMapper allStarMapper = new AllStarMapper();
    private Long requestMonths = 6L;
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    private WebClient.RequestHeadersSpec requestHeadersMock;
    private WebClient.ResponseSpec responseSpecMock;

    @BeforeEach
    void setUp() {
        webClientMock = Mockito.mock(WebClient.class);
        WebClient.Builder mockBuilder = Mockito.mock(WebClient.Builder.class);
        requestHeadersUriMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        requestHeadersMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

        // Mock Setup
        Mockito.when(webClientMock.mutate()).thenReturn(mockBuilder);
        Mockito.when(mockBuilder.build()).thenReturn(webClientMock);
        Mockito.when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        Mockito.when(requestHeadersUriMock.uri((URI) Mockito.any())).thenReturn(requestHeadersMock);
        Mockito.when(requestHeadersMock.retrieve()).thenReturn(responseSpecMock);

        GitHubSearchResponse mockResponse = new GitHubSearchResponse(List.of(
                new GitHubSearchRepository(1L, "Test Repo 1", "Description 1", "https://github.com/test", 100L, "Java", "2023-01-01"),
                new GitHubSearchRepository(2L, "Test Repo 2", "Description 2", "https://github.com/test", 100L, "Java", "2023-01-01")
        ));
        Mockito.when(responseSpecMock.bodyToMono(GitHubSearchResponse.class))
                .thenReturn(Mono.just(mockResponse));
    }

    @Test
    void whenGetAllStarsIncompleteResults_thenReturnsExpectedList() {

        GitHubAllStarsImpl service = new GitHubAllStarsImpl(webClientMock, allStarMapper, requestMonths);

        List<AllStarDto> expectedDtos = List.of(
                new AllStarDto(1L, "Test Repo 1", "Description 1", "https://github.com/test", 100L, "Java", "2023-01-01"),
                new AllStarDto(2L, "Test Repo 2", "Description 2", "https://github.com/test", 100L, "Java", "2023-01-01")
        );

        LocalDate date = LocalDate.now().minusMonths(1);
        Integer top = 5;
        String language = "Java";

        StepVerifier.create(service.getAllStarsIncompleteResults(date, top, language))
                .expectNextMatches(list -> list.size() == 2)
                .expectNextMatches(list -> {
                    return list.equals(expectedDtos);
                });
    }

    @Test
    void whenGetAllStarsCompleteResults_thenReturnsExpectedList() {

         GitHubAllStarsImpl service = new GitHubAllStarsImpl(webClientMock, allStarMapper, requestMonths);

        List<AllStarDto> expectedDtos = List.of(
                new AllStarDto(1L, "Test Repo 1", "Description 1", "https://github.com/test", 100L, "Java", "2023-01-01"),
                new AllStarDto(2L, "Test Repo 2", "Description 2", "https://github.com/test", 100L, "Java", "2023-01-01")
        );

        LocalDate date = LocalDate.now().minusMonths(1);
        Integer top = 5;
        String language = "Java";

        StepVerifier.create(service.getAllStarsCompleteResults(date, top, language))
                .expectNextMatches(list -> list.size() == 2)
                .expectNextMatches(list -> {
                    return list.equals(expectedDtos);
                });
    }

}

