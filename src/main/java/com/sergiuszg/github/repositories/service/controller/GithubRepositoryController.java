package com.sergiuszg.github.repositories.service.controller;

import com.sergiuszg.github.repositories.service.model.dto.RepoDetailsDto;
import com.sergiuszg.github.repositories.service.service.GithubRepositoryService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{user}")
    RepoDetailsDto showUserRepositories(@PathVariable String user) {
        return githubRepositoryService.showUserRepositoriesDetails(user);
    }
}
