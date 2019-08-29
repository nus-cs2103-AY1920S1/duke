package duke.bin.datahandling;

import duke.bin.task.Task;

import java.util.ArrayList;

public class DataStorageStub extends DataStorage {
    public DataStorageStub () {
        super("data/save.txt");
    }

    @Override
    public ArrayList<Task> load() {
        return new ArrayList<Task>();
    }

    @Override
    public void write(ArrayList<Task> taskList) {}
}
