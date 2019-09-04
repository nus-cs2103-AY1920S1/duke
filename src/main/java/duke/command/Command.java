package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

public interface Command {

    public String getTaskType();

    public int getIndex();

    public String getTask();

    public String getDate();

    public String getKeyword();

    public void execute(TaskList tasks, Ui ui);
}
