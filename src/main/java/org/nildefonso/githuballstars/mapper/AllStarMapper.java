package org.nildefonso.githuballstars.mapper;

import org.nildefonso.githuballstars.dto.AllStarDto;
import org.nildefonso.githuballstars.model.GitHubSearchRepository;
import org.springframework.stereotype.Component;

@Component
public class AllStarMapper {
    public AllStarDto convertToAllStarDto(GitHubSearchRepository repo) {
        return new AllStarDto(
                repo.getId(),
                repo.getName(),
                repo.getDescription(),
                repo.getUrl(),
                repo.getStargazers_count(),
                repo.getLanguage(),
                repo.getCreated_at()
        );
    }
}
