package com.sergiuszg.github.repositories.service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepoDetailsDto {

    private final List<RepoDto> repos;
}
