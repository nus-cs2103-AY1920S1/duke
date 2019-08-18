package duke;

import duke.storage.Storage;
import java.util.ArrayList;

public class StorageStub extends Storage {
    StorageStub() {
        super("no path");
    }

    @Override
    public ArrayList<String> read() {
        return null;
    }

    @Override
    public void write(ArrayList<String> lines) {
    }
}
