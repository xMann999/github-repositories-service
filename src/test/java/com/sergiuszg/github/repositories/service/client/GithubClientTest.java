package com.sergiuszg.github.repositories.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ("server.port=8282"))
@AutoConfigureWireMock(port = 8888)
public class GithubClientTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WireMockServer mockServer;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void showUserRepositories_UserFound_RepositoriesReturned() throws JsonProcessingException {
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
        BranchWithoutRepoName branch1 = BranchWithoutRepoName.builder()
                .name("main")
                .commit(new Commit("112412341"))
                .build();
        BranchWithoutRepoName branch2 = BranchWithoutRepoName.builder()
                .name("main")
                .commit(new Commit("54121351"))
                .build();

        RepoDetailsDto expected = RepoDetailsDto.builder()
                .repos(Collections.singletonList(RepoDto.builder()
                        .name("medical-service")
                        .owner(new UserDto("xMann999"))
                        .fork(false)
                        .branches(Collections.singletonList(new BranchDto("main", new CommitDto("112412341"))))
                        .build()))
                .build();

        mockServer.stubFor(get(urlEqualTo("/users/" + user + "/repos")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(List.of(repo1, repo2)))));
        mockServer.stubFor(get(urlEqualTo("/repos/" + user + "/medical-service/branches")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(List.of(branch1)))));
        mockServer.stubFor(get(urlEqualTo("/repos/" + user + "/VeterinaryApp/branches")).willReturn(aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(objectMapper.writeValueAsString(List.of(branch2)))));

        ResponseEntity<RepoDetailsDto> response = restTemplate.exchange("http://localhost:8282/github-repositories/" + user, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(expected, response.getBody());
        Assertions.assertEquals(1, response.getBody().getRepos().size());
    }
}
