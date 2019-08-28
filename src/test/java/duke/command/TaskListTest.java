package duke.command;

import com.sun.source.util.TaskListener;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    protected static class ToDoStub extends Task {
        public ToDoStub(String description) {
            super(description);
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[\u2718] wash dishes";
        }

        @Override
        public String getFileStringFormat() {
            return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
        }
    }

    @Test
    public void addToTaskList_inputTask_success() {
        TaskList tasks =  new TaskList(new ArrayList<Task>());
        tasks.add(new ToDoStub("wash dishes"));
        assertEquals(1, tasks.getList().size());
    }
}