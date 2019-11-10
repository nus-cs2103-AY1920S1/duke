import duke.storage.StorageInterface;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class StorageStub implements StorageInterface {
    @Override
    public ArrayList<Task> load() throws IOException {
        return null;
    }

    @Override
    public void save(TaskList taskList) {
    }
}
