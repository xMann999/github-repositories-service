package com.sergiuszg.github.repositories.service.service;

import com.sergiuszg.github.repositories.service.client.GithubClient;
import com.sergiuszg.github.repositories.service.mapper.GithubRepositoryComponentMapper;
import com.sergiuszg.github.repositories.service.mapper.GithubRepositoryMapper;
import com.sergiuszg.github.repositories.service.model.dto.RepoDetailsDto;
import com.sergiuszg.github.repositories.service.model.dto.RepoDto;
import com.sergiuszg.github.repositories.service.model.dto.component.BranchDto;
import com.sergiuszg.github.repositories.service.model.dto.component.CommitDto;
import com.sergiuszg.github.repositories.service.model.dto.component.UserDto;
import com.sergiuszg.github.repositories.service.model.internal.Branch;
import com.sergiuszg.github.repositories.service.model.internal.BranchWithoutRepoName;
import com.sergiuszg.github.repositories.service.model.internal.Repo;
import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import com.sergiuszg.github.repositories.service.model.internal.component.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GithubRepositoriesServiceTest {

    @InjectMocks
    GithubRepositoryService githubRepositoryService;

    @Mock
    GithubClient githubClient;

    @Mock
    GithubRepositoryComponentMapper githubRepositoryComponentMapper;

    @Mock
    GithubRepositoryMapper githubRepositoryMapper;

    @Test
    void showUserRepositoriesDetails_UserFound_RepositoriesWhichAreNotForkReturned() throws Exception {
        String user = "xMann999";
        Repo repo1 = Repo.builder()
                .name("medical-service")
                .owner(new User("xMann999"))
                .fork(false)
                .build();
        Repo repo2 = Repo.builder()
                .name("VeterinaryApp")
                .owner(new User("konradgab"))
                .fork(true)
                .build();
        BranchWithoutRepoName branchWithoutRepoName = BranchWithoutRepoName.builder()
                .name("main")
                .commit(new Commit("112412341"))
                .build();
        Branch branch = Branch.builder()
                .name("main")
                .repositoryName("medical-service")
                .commit(new Commit("112412341"))
                .build();
        User owner = new User("xMann999");
        UserDto ownerDto = new UserDto("xMann999");
        RepoDetailsDto repoDetailsDto = RepoDetailsDto.builder()
                .repos(Collections.singletonList(RepoDto.builder()
                        .name("medical-service")
                        .owner(new UserDto("xMann999"))
                        .fork(false)
                        .branches(Collections.singletonList(new BranchDto("main", new CommitDto("112412341"))))
                        .build()))
                .build();

        when(githubClient.showUserRepositories(eq(user))).thenReturn(List.of(repo1, repo2));
        when(githubClient.showUserBranches(eq(user), eq(repo1.getName()))).thenReturn(List.of(branchWithoutRepoName));
        when(githubRepositoryComponentMapper.connectBranchToRepository(eq(branchWithoutRepoName), eq(repo1.getName()))).thenReturn(branch);
        when(githubRepositoryMapper.map(List.of(branch), List.of(repo1))).thenReturn(repoDetailsDto);

        var result = githubRepositoryService.showUserRepositoriesDetails(user);

        assertEquals(1, result.getRepos().size());
        assertEquals("medical-service", result.getRepos().get(0).getName());
        assertEquals("112412341", result.getRepos().get(0).getBranches().get(0).getCommit().getSha());
    }
}
