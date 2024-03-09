package org.nildefonso.githuballstars.service;

import org.nildefonso.githuballstars.dto.AllStarDto;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface GitHubAllStarsService {
    Mono<List<AllStarDto>> getAllStarsIncompleteResults(LocalDate date, Integer top, String language);

    Mono<List<AllStarDto>> getAllStarsCompleteResults(LocalDate date, Integer top, String language);
}
