package puke;

import puke.command.ResponseStrings;
import puke.task.TaskList;

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
