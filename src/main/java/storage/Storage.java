package storage;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    public String path;

    public Storage(String filePath){
        this.path = filePath;
    }

    public File load(){
        return new File(path);
    }
}
