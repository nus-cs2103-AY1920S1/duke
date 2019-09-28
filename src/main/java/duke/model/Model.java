package duke.model;

import java.io.Serializable;

import duke.parser.Aliases;
import duke.tasks.TaskList;

public class Model implements Serializable {
    private Aliases aliases = new Aliases();
    private TaskList taskList = new TaskList();

    public Aliases getAliases() {
        return aliases;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
