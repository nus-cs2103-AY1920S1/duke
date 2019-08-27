package duke.task;

import java.text.SimpleDateFormat;

public abstract class Task {
    public String name;
    String type;
    public Boolean done;
    SimpleDateFormat formatter;

    public Task(String name, SimpleDateFormat formatter) {
        this.name = name;
        this.done = false;
        this.formatter = formatter;
    }

    public abstract String saveText();
}
