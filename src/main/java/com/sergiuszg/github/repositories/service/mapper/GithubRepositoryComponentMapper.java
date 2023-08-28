package com.sergiuszg.github.repositories.service.mapper;

import com.sergiuszg.github.repositories.service.model.dto.component.BranchDto;
import com.sergiuszg.github.repositories.service.model.dto.component.CommitDto;
import com.sergiuszg.github.repositories.service.model.dto.component.UserDto;
import com.sergiuszg.github.repositories.service.model.internal.Branch;
import com.sergiuszg.github.repositories.service.model.internal.BranchWithoutRepoName;
import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import com.sergiuszg.github.repositories.service.model.internal.component.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GithubRepositoryComponentMapper {

    CommitDto commitToCommitDto (Commit commit);

    UserDto userToUserDto (User user);

    List<BranchDto> branchListToBranchDtoList (List<Branch> branches);

    default Branch connectBranchToRepository (BranchWithoutRepoName branch, String repoName) {
        return Branch.builder()
                .commit(branch.getCommit())
                .name(branch.getName())
                .repositoryName(repoName)
                .build();
    }
}
