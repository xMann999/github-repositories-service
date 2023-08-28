package com.sergiuszg.github.repositories.service.model.internal;

import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class BranchWithoutRepoName {

    private final String name;
    private final Commit commit;
}
