package org.nildefonso.githuballstars.service.impl;

import org.nildefonso.githuballstars.dto.AllStarDto;
import org.nildefonso.githuballstars.mapper.AllStarMapper;
import org.nildefonso.githuballstars.model.GitHubSearchResponse;
import org.nildefonso.githuballstars.service.GitHubAllStarsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ControllerAdvice
public class GitHubAllStarsImpl implements GitHubAllStarsService {
    private static final Logger log = LoggerFactory.getLogger(GitHubAllStarsImpl.class);

    private final WebClient allStarsClient;
    private final AllStarMapper allStarMapper;
    private final Long requestMonths;

    public GitHubAllStarsImpl(WebClient allStarsClient,
                              AllStarMapper allStarMapper,
                              @Value("${github.requestMonths}") Long requestMonths) {
        this.allStarsClient = allStarsClient;
        this.allStarMapper = allStarMapper;
        this.requestMonths = requestMonths;
    }

    public Mono<List<AllStarDto>> getAllStarsIncompleteResults(LocalDate date, Integer top, String language) {
        String query = "created:>=" + date + (language != null && !language.isEmpty() ? "+language:" + language : "");

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.github.com/search/repositories")
                .queryParam("q", query)
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .queryParam("per_page", top)
                .queryParam("page", 1)
                .build().toUri();

        log.info("Requesting GitHub API with URI: {}", uri);

        return allStarsClient.mutate().build().get()
                .uri(uri)
                .retrieve()
                .bodyToMono(GitHubSearchResponse.class)
                .map(response -> response.items.stream().map(allStarMapper::convertToAllStarDto).collect(Collectors.toList()));
    }

    public Mono<List<AllStarDto>> getAllStarsCompleteResults(LocalDate startDate, Integer top, String language) {
        String languageUri = (language != null && !language.isEmpty() ? "+language:" + language : "");

        List<LocalDate[]> dateRanges = createDateRanges(startDate);

        Flux<GitHubSearchResponse> responseFlux = Flux.fromIterable(dateRanges)
                .flatMap(dateRange -> {
                    String query = String.format("created:%s..%s", dateRange[0], dateRange[1]) + languageUri;

                    URI uri = UriComponentsBuilder
                            .fromUriString("https://api.github.com/search/repositories")
                            .queryParam("q", query)
                            .queryParam("sort", "stars")
                            .queryParam("order", "desc")
                            .queryParam("per_page", top)
                            .queryParam("page", 1)
                            .build().toUri();

                    log.info("Requesting GitHub API with URI: {}", uri);

                    return allStarsClient.get()
                            .uri(uri)
                            .retrieve()
                            .bodyToMono(GitHubSearchResponse.class);
                });

        return responseFlux
                .flatMapIterable(GitHubSearchResponse::getItems)
                .collectSortedList((repo1, repo2) -> repo2.getStargazers_count().compareTo(repo1.getStargazers_count()))
                .map(sortedList -> sortedList.stream()
                        .limit(top)
                        .map(allStarMapper::convertToAllStarDto)
                        .collect(Collectors.toList()));
    }

    private List<LocalDate[]> createDateRanges(LocalDate startDate) {
        List<LocalDate[]> dateRanges = new ArrayList<>();
        LocalDate endDate = LocalDate.now();
        LocalDate tempEnd;

        while (startDate.isBefore(endDate)) {
            tempEnd = startDate.plusMonths(requestMonths).minusDays(1);
            if (tempEnd.isAfter(endDate)) {
                tempEnd = endDate;
            }
            dateRanges.add(new LocalDate[]{startDate, tempEnd});
            startDate = tempEnd.plusDays(1);
        }

        return dateRanges;
    }
}
