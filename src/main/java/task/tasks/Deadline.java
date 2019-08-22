package task.tasks;

import task.Task;

import java.util.Optional;

public class Deadline extends Task {
    private String by;

    public Deadline(String arguments) {
        super(getDescription(arguments), TaskKeyword.DEADLINE);
        this.by = getTiming(arguments);
    }

    @Override
    protected String getTaskStringCode() {
        return "D";
    }

    @Override
    protected Optional<String> getTaskExtraDetails() {
        return Optional.of(String.format("by: %s", by));
    }

    private static String getDescription(String arguments) {
        return arguments.split(" /by ")[0];
    }

    private static String getTiming(String arguments) {
        return arguments.split(" /by ")[1];
    }
}
