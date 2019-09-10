package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

/**
 * An interface for command classes.
 */
public interface Command {

    public String getTaskType();

    public int getIndex();

    public String getDescription();

    public String getKeyword();

    public void execute(TaskList tasks, Ui ui);
}