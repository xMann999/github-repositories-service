package com.sergiuszg.github.repositories.service.model.dto.component;

import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import lombok.Data;

@Data
public class BranchDto {

    private final String name;
    private final CommitDto commit;
}
