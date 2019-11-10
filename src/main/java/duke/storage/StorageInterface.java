package duke.storage;

import duke.task.Task;
import duke.tasklist.TaskList;
import java.io.IOException;
import java.util.ArrayList;

public interface StorageInterface {

    public ArrayList<Task> load() throws IOException;

    public void save(TaskList taskList);

}
