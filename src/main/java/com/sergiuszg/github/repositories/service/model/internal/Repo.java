package com.sergiuszg.github.repositories.service.model.internal;

import com.sergiuszg.github.repositories.service.model.internal.component.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Repo {

    private final String name;
    private final String description;
    private final User owner;
    private final boolean fork;
}
