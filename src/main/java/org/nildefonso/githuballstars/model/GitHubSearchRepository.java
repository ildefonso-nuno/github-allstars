package org.nildefonso.githuballstars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GitHubSearchRepository {
    private Long id;
    private String name;
    private String description;
    private String url;
    private Long stargazers_count;
    private String language;
    private String created_at;
}
