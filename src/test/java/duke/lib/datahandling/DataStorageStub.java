package duke.lib.datahandling;

import duke.lib.task.Task;

import java.util.ArrayList;

public class DataStorageStub extends DataStorage {

    @Override
    public ArrayList<Task> load() {
        return new ArrayList<Task>();
    }

    @Override
    public void write(ArrayList<Task> taskList) {

    }
}
