package org.nildefonso.githuballstars.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllStarDtoTests {
    @Test
    public void testGettersAndSetters() {
        AllStarDto dto = new AllStarDto();
        dto.setId(1L);
        dto.setName("Test Project");
        dto.setDescription("A sample project");
        dto.setUrl("http://example.com");
        dto.setStargazers_count(100L);
        dto.setLanguage("Java");
        dto.setCreatedDate("2021-01-01");

        assertEquals(1L, dto.getId());
        assertEquals("Test Project", dto.getName());
        assertEquals("A sample project", dto.getDescription());
        assertEquals("http://example.com", dto.getUrl());
        assertEquals(100L, dto.getStargazers_count());
        assertEquals("Java", dto.getLanguage());
        assertEquals("2021-01-01", dto.getCreatedDate());
    }
}
