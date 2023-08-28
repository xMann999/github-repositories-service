package com.sergiuszg.github.repositories.service.model.dto;

import com.sergiuszg.github.repositories.service.model.dto.component.BranchDto;
import com.sergiuszg.github.repositories.service.model.dto.component.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepoDto {

    private final String name;
    private final String description;
    private final UserDto owner;
    private final boolean fork;
    private final List<BranchDto> branches;
}
