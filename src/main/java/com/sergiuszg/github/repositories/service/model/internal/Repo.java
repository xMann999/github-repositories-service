package com.sergiuszg.github.repositories.service.model.internal;

import com.sergiuszg.github.repositories.service.model.internal.component.User;
import lombok.Data;

@Data
public class Repo {

    private final String name;
    private final String description;
    private final User owner;
    private final boolean fork;
}
