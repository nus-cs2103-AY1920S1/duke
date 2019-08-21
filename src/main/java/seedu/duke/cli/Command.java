package seedu.duke.cli;

import seedu.duke.Task;

import java.util.List;

public interface Command {
    boolean execute(List<Task> taskList);
}
