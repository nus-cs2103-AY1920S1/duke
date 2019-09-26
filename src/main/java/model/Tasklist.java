package model;

import java.util.ArrayList;
import java.util.List;

public class Tasklist extends ArrayList<Task> {
    public Tasklist() {
        super();
    }

    public Tasklist(List<Task> tasks) {
        super(tasks);
    }
}
