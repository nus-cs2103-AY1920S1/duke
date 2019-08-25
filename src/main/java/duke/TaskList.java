package duke;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayList<Task> getAllTasks() {
        return tasks;
    }

    void setTaskDone(boolean isDone, int index) {
        tasks.get(index).setDone(isDone);
    }
}
