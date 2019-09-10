package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

public interface Command {

    public String getTaskType();

    public int getIndex();

    public String getDescription();

    public String getKeyword();

    public void execute(TaskList tasks, Ui ui);
}
