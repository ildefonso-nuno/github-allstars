package org.nildefonso.githuballstars.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GitHubSearchResponse {
    public List<GitHubSearchRepository> items;
}
