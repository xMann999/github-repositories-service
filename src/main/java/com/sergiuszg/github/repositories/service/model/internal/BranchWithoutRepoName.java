package com.sergiuszg.github.repositories.service.model.internal;

import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import lombok.Data;

@Data
public class BranchWithoutRepoName {

    private final String name;
    private final Commit commit;
}
