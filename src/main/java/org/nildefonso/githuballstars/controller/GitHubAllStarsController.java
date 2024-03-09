package org.nildefonso.githuballstars.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.nildefonso.githuballstars.dto.AllStarDto;
import org.nildefonso.githuballstars.service.GitHubAllStarsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "REST APIs for GitHub Allstars service ",
        description = "Get GitHub All Stars"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/github")
@Validated
public class GitHubAllStarsController {

    private GitHubAllStarsService gitHubAllStarsService;

    //get all stars by date, number and programming language RESTAPI - Possible Incomplete Results
    @Operation(
            summary = "Get All Stars REST API - Possible Incomplete Results",
            description = "Get github stars by date, top number and programming language from github RESTAPI"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/getallstars/incompleteresults")
    public Mono<ResponseEntity<List<AllStarDto>>> getAllStarsIncompleteResults(
            @RequestParam(value = "date", required = false, defaultValue = "2007-01-01")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "top", defaultValue = "10")
            @Min(1) @Max(100) Integer top,
            @RequestParam(value = "language", required = false) String language) {
        return gitHubAllStarsService.getAllStarsIncompleteResults(date, top, language)
                .map(ResponseEntity::ok);
    }

    //get all stars by date, number and programming language RESTAPI - Possible Complete Results
    @Operation(
            summary = "Get All Stars REST API - Possible Incomplete Results",
            description = "Get github stars by date, top number and programming language from github RESTAPI"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/getallstars/completeresults")
    public Mono<ResponseEntity<List<AllStarDto>>> getAllStarsCompleteResults(
            @RequestParam(value = "date", required = false, defaultValue = "2007-01-01")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "top", defaultValue = "10")
            @Min(1) @Max(100) Integer top,
            @RequestParam(value = "language", required = false) String language) {
        return gitHubAllStarsService.getAllStarsCompleteResults(date, top, language)
                .map(ResponseEntity::ok);
    }
}
