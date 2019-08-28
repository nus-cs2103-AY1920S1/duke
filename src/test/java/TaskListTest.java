import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    private TaskList taskList;
    public TaskListTest() {
        taskList = new TaskList();
        taskList.add(new ToDoTask("Say hi"));
        taskList.add(new DeadlineTask("task", "1/1/1991 1234"));
        taskList.add(new EventTask("sale", "1/1/1991 1234"));
    }

    @Test
    public void testTaskListSize() {
        assertEquals(3, taskList.size());
    }

    @Test
    public void testGetTaskList() {
        assertEquals("[D][✗] task (by: 1/1/1991 1234)", taskList.get(1).toString());
    }
    /*
    @Test
    public void testStringConversion() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        taskList.print();
        String expectedOutput = "Here are the tasks in your list:\n" +
                                "1.[T][✗] Say hi\n" +
                                "2.[D][✗] task (by: 1/1/1991 1234)\n" +
                                "3.[E][✗] sale (at: 1/1/1991 1234)\n";
        assertEquals(expectedOutput, outContent.toString());
    }
     */
}
