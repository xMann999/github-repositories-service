package com.sergiuszg.github.repositories.service.mapper;

import com.sergiuszg.github.repositories.service.model.dto.RepoDetailsDto;
import com.sergiuszg.github.repositories.service.model.dto.RepoDto;
import com.sergiuszg.github.repositories.service.model.internal.Branch;
import com.sergiuszg.github.repositories.service.model.internal.Repo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GithubRepositoryMapper {

    private final GithubRepositoryComponentMapper githubRepositoryComponentMapper;

    public RepoDetailsDto map(List<Branch> branches, List<Repo> repos) {
        return RepoDetailsDto.builder()
                .repos(repos.stream()
                        .map(repo -> RepoDto.builder()
                                .name(repo.getName())
                                .owner(githubRepositoryComponentMapper.userToUserDto(repo.getOwner()))
                                .fork(repo.isFork())
                                .description(repo.getDescription())
                                .branches(githubRepositoryComponentMapper.branchListToBranchDtoList(branches.stream()
                                        .filter(branch -> branch.getRepositoryName().equals(repo.getName()))
                                        .toList()))
                                .build())
                        .filter(repoDto -> !repoDto.isFork())
                        .toList())
                .build();
    }
}
