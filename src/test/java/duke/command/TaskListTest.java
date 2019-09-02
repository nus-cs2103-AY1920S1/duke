package duke.command;

import duke.task.Task;
import duke.task.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests TaskList class.
 */
class TaskListTest {
    /**
     * Tests if TaskList successfully add a new Task.
     */
    @Test
    public void taskList_invalidCommand_exceptionThrown() {
        /**
         * Stub for Task.
         */
        class TaskStub extends Task {
            /**
             * Constructs a new TaskStub.
             *
             * @param desc description of the task
             * @param bool whether or not the task has been done
             * @param information extra info of the TaskStub
             */
            public TaskStub(String desc, boolean bool, String information) {
                super();
                description = desc;
                isDone = bool;
                info = information;
            }

            /**
             * Returns null.
             */
            @Override
            public String getFileStringFormat() {
                return null;
            }

        }

        /**
         * Stub for Event.
         */
        class EventStub extends TaskStub {
            /**
             * Constructs a new EventStub.
             *
             * @param description description of the task
             * @param isDone      whether or not the task has been done
             * @param info extra info of the EventStub
             */
            public EventStub(String description, boolean isDone, String info) {
                super(description, isDone, info);
                type = Type.EVENT;
            }
        }

        TaskList taskList = new TaskList();
        taskList.addTask(new EventStub("Do this thing",  false, ""));
        assertEquals(1, taskList.getSize());
    }
}