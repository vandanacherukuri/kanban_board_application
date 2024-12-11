package com.pm_app.backend.models;

import java.io.Serializable;

public enum PermissionEnum implements Serializable {
    USER(0),
    PROJECT_MANAGER(1);

    private final int level;

    PermissionEnum(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return String.valueOf(level);
    }
}
