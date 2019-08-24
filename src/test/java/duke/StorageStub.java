package duke;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import java.util.List;

public class StorageStub extends Storage {
    StorageStub() {
        super("");
    }

    @Override
    public List<Task> load() {
        return null;
    }

    @Override
    public void save(TaskList tasks) {
    }
}
