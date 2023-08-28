package com.sergiuszg.github.repositories.service.controller;

import com.sergiuszg.github.repositories.service.model.dto.RepoDetailsDto;
import com.sergiuszg.github.repositories.service.model.dto.RepoDto;
import com.sergiuszg.github.repositories.service.service.GithubRepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github-repositories")
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    @Operation(summary = "Show user repositories", tags = "Repositories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RepoDto.class))}),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "406", description = "Not supported format", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @GetMapping("/{user}")
    public RepoDetailsDto showUserRepositories(@PathVariable String user) {
        return githubRepositoryService.showUserRepositoriesDetails(user);
    }
}
