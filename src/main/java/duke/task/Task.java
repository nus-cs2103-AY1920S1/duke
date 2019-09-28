package duke.task;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public abstract class Task implements Serializable {
    public String name;
    String type;
    public Boolean done;
    SimpleDateFormat formatter;
    /**
     * defines a generic constructor for all Task subclasses.
     * @param name String name.
     * @param formatter DateTime formatter.
     */
    Task(String name, SimpleDateFormat formatter) {
        this.name = name;
        this.done = false;
        this.formatter = formatter;
    }
}
