package duke.main;

import java.io.FileWriter;

public class Storage {
    private String filePath;
    private FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
}
