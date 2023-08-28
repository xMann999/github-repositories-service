package com.sergiuszg.github.repositories.service.model.internal;

import com.sergiuszg.github.repositories.service.model.internal.component.Commit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {

    private final String name;
    private final Commit commit;
    private final String repositoryName;
}
