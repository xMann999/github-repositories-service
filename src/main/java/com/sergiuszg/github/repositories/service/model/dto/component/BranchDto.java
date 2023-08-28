package com.sergiuszg.github.repositories.service.model.dto.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import lombok.Data;

@Data
public class BranchDto {

    private final String name;
    @JsonProperty(value = "last_commit")
    private final CommitDto commit;
}
