import java.util.ArrayList;

public class StorageStub extends Storage {

    public StorageStub() {
        super("data/test1.txt");
    }

    @Override
    public ArrayList<Task> createTasksFromFile() {
        return super.createTasksFromFile();
    }
}
