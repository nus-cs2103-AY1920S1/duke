package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

/**
 * An interface for command classes.
 */
public interface Command {

    String getTaskType();

    int getIndex();

    String getDescription();

    String getKeyword();

    void execute(TaskList tasks, Ui ui);
}