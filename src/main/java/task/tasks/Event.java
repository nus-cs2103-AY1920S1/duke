package task.tasks;

import task.Task;

import java.util.Optional;

public class Event extends Task {
    private String at;

    public Event(String arguments) {
        super(getDescription(arguments), TaskKeyword.EVENT);
        this.at = getTiming(arguments);
    }

    @Override
    protected String getTaskStringCode() {
        return "E";
    }

    @Override
    protected Optional<String> getTaskExtraDetails() {
        return Optional.of(String.format("at: %s", at));
    }

    private static String getDescription(String arguments) {
        return arguments.split(" /at ")[0];
    }

    private static String getTiming(String arguments) {
        return arguments.split(" /at ")[1];
    }
}
