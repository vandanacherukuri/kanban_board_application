package com.pm_app.backend.models;

public enum StateEnum {
    NOT_STARTED {
        public String toString() {
            return "not started";
        }
    },

    IN_PROGRESS {
        public String toString() {
            return "in progress";
        }
    },

    DONE {
        public String toString() {
            return "done";
        }
    }
}
