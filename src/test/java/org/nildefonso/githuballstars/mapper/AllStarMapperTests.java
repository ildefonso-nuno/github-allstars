package org.nildefonso.githuballstars.mapper;

import org.junit.jupiter.api.Test;
import org.nildefonso.githuballstars.dto.AllStarDto;
import org.nildefonso.githuballstars.model.GitHubSearchRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllStarMapperTests {
    @Test
    public void convertToAllStarDto_CorrectMapping() {
        // Setup: Create a GitHubSearchRepository object with test data
        GitHubSearchRepository repo = new GitHubSearchRepository();
        repo.setId(1L);
        repo.setName("Test Repo");
        repo.setDescription("A test repository");
        repo.setUrl("https://github.com/testrepo");
        repo.setStargazers_count(100L);
        repo.setLanguage("Java");
        repo.setCreated_at("2023-01-01");

        // Instantiate the mapper
        AllStarMapper mapper = new AllStarMapper();

        // Act: Convert the GitHubSearchRepository object to an AllStarDto object
        AllStarDto dto = mapper.convertToAllStarDto(repo);

        // Assert: Verify that the properties have been correctly mapped
        assertEquals(repo.getId(), dto.getId());
        assertEquals(repo.getName(), dto.getName());
        assertEquals(repo.getDescription(), dto.getDescription());
        assertEquals(repo.getUrl(), dto.getUrl());
        assertEquals(repo.getStargazers_count(), dto.getStargazers_count());
        assertEquals(repo.getLanguage(), dto.getLanguage());
        assertEquals(repo.getCreated_at(), dto.getCreatedDate());
    }
}
