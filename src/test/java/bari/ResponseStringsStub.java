package bari;

import bari.command.ResponseStrings;
import bari.task.TaskList;

public class ResponseStringsStub extends ResponseStrings {
    @Override
    public void add(String line) {
    }

    @Override
    public void listTasks(TaskList tl) {
    }

    @Override
    public void numTasksInList(TaskList tl) {
    }

    @Override
    public String toString() {
        return "string representation of ResponseStrings";
    }
}
