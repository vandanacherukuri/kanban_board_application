package com.pm_app.backend.models;

import com.pm_app.backend.dtos.SimplifiedDto;

public interface Substitutable {
    Substitutable substituteStaticMembers(SimplifiedDto source);
}
