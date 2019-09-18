package duke.command;

import duke.model.TaskList;
import duke.model.task.Task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for TaskList class.
 */
class TaskListTest {

    /**
     * A stub for to-do class.
     */
    protected static class ToDoStub extends Task {
        ToDoStub(String description) {
            super(description);
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[x] wash dishes";
        }

        @Override
        public String getFileStringFormat() {
            return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
        }
    }

    /**
     * Add a task to TaskList.
     *
     * @result Task will be added without any error.
     */
    @Test
    void addToTaskList_inputTask_success() {
        TaskList tasks =  new TaskList(new ArrayList<Task>());
        tasks.add(new ToDoStub("wash dishes"));
        assertEquals(1, tasks.getList().size());
    }
}