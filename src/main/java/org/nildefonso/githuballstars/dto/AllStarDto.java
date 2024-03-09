package org.nildefonso.githuballstars.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "AllStar Project Model Information")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllStarDto {
    @Schema(description = "Project Id")
    private Long id;

    @Schema(description = "Project Name")
    @NotEmpty(message = "Project Name should not be null or empty")
    private String name;

    @Schema(description = "Project Description")
    private String description;

    @Schema(description = "Project URL")
    @NotEmpty(message = "Project URL should not be null or empty")
    private String url;

    @Schema(description = "Project Stars")
    @NotEmpty(message = "Project Stars should not be null or empty")
    private Long stargazers_count;

    @Schema(description = "Project Programming Language")
    private String language;

    @Schema(description = "Project Created Date")
    @NotEmpty(message = "Project Stars should not be null or empty")
    private String createdDate;
}
