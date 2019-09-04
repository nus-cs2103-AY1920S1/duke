package duke;

import java.util.List;

public class StorageStub extends Storage {
    public List<String> stored;

    public StorageStub() {
        super("StorageStubTemp.txt");
    }

    @Override
    public void store(List<String> lines) {
        stored = lines;
    }
}
