package duke.task;

import duke.command.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a JUnit test for TaskList.
 */

class TaskListTest {

    /**
     * Test add method in TaskList.
     */

    @Test
    public void taskList_invalidCommand_exceptionThrown() {

        /**
         * Stub for Task.
         */
        
        class TaskStub extends Task {
            public TaskStub(String desc) {
                description = desc;
                isDone = false;
                num = 0;
            }
            
            public String format() {
                return null;
            }

            @Override
            public int getDiffDays() {
                return 0;
            }
        }

        /**
         * Stub for ToDos.
         */

        class ToDosStub extends TaskStub {
            public ToDosStub(String desc) {
                super(desc);
            }
        }

        TaskList taskList = new TaskList();
        taskList.add(new ToDosStub("do"));
        assertEquals(1, taskList.list.size());
    }

}