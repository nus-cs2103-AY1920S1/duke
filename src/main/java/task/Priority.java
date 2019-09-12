package task;

import exception.DukeException;

public enum Priority {
    HIGH, MEDIUM, LOW;

    public static Priority parsePriority(String string) throws DukeException {
        string.toLowerCase();
        switch (string) {
        case "high":
            return Priority.HIGH;
        case "medium":
            return Priority.MEDIUM;
        case "low":
            return Priority.LOW;
        case "null":
            return null;
        default:
            throw new DukeException("Priority value must be high, medium, or low!");
        }
    }
}
