package duke;

import duke.task.Task;
import duke.task.TaskMock;

import java.util.ArrayList;

public class StorageMock extends Storage {
    public StorageMock(String filePath) {
        super(filePath);
    }

    @Override
    public ArrayList<Task> load(){
        ArrayList<Task> storage = new ArrayList<>();
        storage.add(new TaskMock(""));

        return storage;
    }
}
