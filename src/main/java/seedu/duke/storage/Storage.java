package seedu.duke.storage;

import java.io.File;

public class Storage {
    public String path;

    public Storage(String filePath){
        this.path = filePath;
    }

    public File load(){
        return new File(path);
    }
}
