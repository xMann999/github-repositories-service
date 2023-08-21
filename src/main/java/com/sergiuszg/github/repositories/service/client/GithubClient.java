package com.sergiuszg.github.repositories.service.client;

import com.sergiuszg.github.repositories.service.config.FeignConfig;
import com.sergiuszg.github.repositories.service.model.internal.BranchWithoutRepoName;
import com.sergiuszg.github.repositories.service.model.internal.Repo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "repository-details", url = "${api.github.url}", configuration = FeignConfig.class)
public interface GithubClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    List<Repo> showUserRepositories(@PathVariable String username);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<BranchWithoutRepoName> showUserBranches(@PathVariable String owner, @PathVariable String repo);
}
