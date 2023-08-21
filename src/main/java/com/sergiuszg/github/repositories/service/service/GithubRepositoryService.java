package com.sergiuszg.github.repositories.service.service;

import com.sergiuszg.github.repositories.service.client.GithubClient;
import com.sergiuszg.github.repositories.service.exception.NoContentException;
import com.sergiuszg.github.repositories.service.mapper.GithubRepositoryComponentMapper;
import com.sergiuszg.github.repositories.service.mapper.GithubRepositoryMapper;
import com.sergiuszg.github.repositories.service.model.dto.RepoDetailsDto;
import com.sergiuszg.github.repositories.service.model.internal.Branch;
import com.sergiuszg.github.repositories.service.model.internal.BranchWithoutRepoName;
import com.sergiuszg.github.repositories.service.model.internal.Repo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class GithubRepositoryService {

    private final GithubClient githubclient;
    private final GithubRepositoryMapper githubRepositoryMapper;
    private final GithubRepositoryComponentMapper githubRepositoryComponentMapper;

    public RepoDetailsDto showUserRepositoriesDetails(String owner) {
        List<Repo> repos = githubclient.showUserRepositories(owner);
        if (repos.size() == 0) {
            throw new NoContentException("User does not have any repository");
        }
        List<Branch> branches = new ArrayList<>();

        for (Repo repo : repos) {
            String repoName = repo.getName();
            List<BranchWithoutRepoName> branchWithoutRepoNames = githubclient.showUserBranches(owner, repoName);
            List<Branch> branchesWithRepoNames = branchWithoutRepoNames.stream()
                    .map(branchWithoutRepoName -> githubRepositoryComponentMapper.connectBranchToRepository(branchWithoutRepoName, repoName))
                    .toList();
            branches.addAll(branchesWithRepoNames);
        }
        return githubRepositoryMapper.map(branches, repos);
    }
}
