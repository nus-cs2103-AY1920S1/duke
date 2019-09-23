import exceptions.InvalidInputException;
import exceptions.InvalidItemException;
import exceptions.MissingInputException;
import org.junit.jupiter.api.Test;

import task.DukeDate;
import task.Deadline;
import task.Event;
import task.DukeTime;
import task.TaskList;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList taskListTest1 = new TaskList();

    private static String TODO1 = "_______________________________________________________\n"
            + "     Got it. I've added this task: \n"
            + "       " + TestCase.TODO_SUCCESSFUL_OUTPUT + "\n"
            + "     Now you have 1 task(s) in the list.\n"
            + "_______________________________________________________\n";

    private static String EVENT2 = "_______________________________________________________\n"
            + "     Got it. I've added this task: \n"
            + "       " + TestCase.EVENT_SUCCESSFUL_OUTPUT + "\n"
            + "     Now you have 2 task(s) in the list.\n"
            + "_______________________________________________________\n";

    private static String DEADLINE3 = "_______________________________________________________\n"
            + "     Got it. I've added this task: \n"
            + "       " + TestCase.DEADLINE_SUCCESSFUL_OUTPUT + "\n"
            + "     Now you have 3 task(s) in the list.\n"
            + "_______________________________________________________\n";

    private static String TODO_DELETE = "_______________________________________________________\n"
            + "     Noted. I've removed this task: \n"
            + "       " + TestCase.TODO_SUCCESSFUL_OUTPUT + "\n"
            + "     Now you have 2 task(s) in the list.\n"
            + "_______________________________________________________\n";

    private static String EVENT_DELETE = "_______________________________________________________\n"
            + "     Noted. I've removed this task: \n"
            + "       " + TestCase.EVENT_SUCCESSFUL_OUTPUT + "\n"
            + "     Now you have 1 task(s) in the list.\n"
            + "_______________________________________________________\n";

    private static String DEADLINE_DELETE = "_______________________________________________________\n"
            + "     Noted. I've removed this task: \n"
            + "       " + TestCase.DEADLINE_SUCCESSFUL_OUTPUT + "\n"
            + "     Now you have 0 task(s) in the list.\n"
            + "_______________________________________________________\n";

    private static String DEADLINE_DONE = "_______________________________________________________\n"
            + "     Nice! I've marked this task as done:\n"
            + "       " + TestCase.DEADLINE_DONE_OUTPUT + "\n"
            + "_______________________________________________________\n";

    private static String EVENT_DONE = "_______________________________________________________\n"
            + "     Nice! I've marked this task as done:\n"
            + "       " + TestCase.EVENT_DONE_OUTPUT + "\n"
            + "_______________________________________________________\n";

    private static String TODO_DONE = "_______________________________________________________\n"
            + "     Nice! I've marked this task as done:\n"
            + "       " + TestCase.TODO_DONE_OUTPUT + "\n"
            + "_______________________________________________________\n";

    @Test
    public void addTask_success() throws InvalidInputException, MissingInputException {
        assertEquals(TODO1, taskListTest1.addTask(new Todo(1, TestCase.TODO_DESCRIPTION, "T")));
        assertEquals(EVENT2, taskListTest1.addTask(new Event(2, TestCase.EVENT_DESCRIPTION,
                DukeDate.processDate(TestCase.DATE), DukeTime.processTime(TestCase.TIME), "E")));
        assertEquals(DEADLINE3, taskListTest1.addTask(new Deadline(3, TestCase.DEADLINE_DESCRIPTION,
                DukeDate.processDate(TestCase.DATE), DukeTime.processTime(TestCase.TIME), "D")));
    }

    private void initialiseTasksForTest() throws InvalidInputException, MissingInputException {
        taskListTest1.addTask(new Todo(1, TestCase.TODO_DESCRIPTION, "T"));
        taskListTest1.addTask(new Event(2, TestCase.EVENT_DESCRIPTION,
                DukeDate.processDate(TestCase.DATE), DukeTime.processTime(TestCase.TIME), "E"));
        taskListTest1.addTask(new Deadline(3, TestCase.DEADLINE_DESCRIPTION,
                DukeDate.processDate(TestCase.DATE), DukeTime.processTime(TestCase.TIME), "D"));
    }

    @Test
    public void deleteTask_success() throws InvalidInputException, MissingInputException, InvalidItemException {
        initialiseTasksForTest();
        assertEquals(TODO_DELETE, taskListTest1.deleteTask(1));
        assertEquals(EVENT_DELETE, taskListTest1.deleteTask(1));
        assertEquals(DEADLINE_DELETE, taskListTest1.deleteTask(1));
    }

    @Test
    public void deleteTask_throwsException() throws InvalidInputException, MissingInputException {
        initialiseTasksForTest();
        assertThrows(InvalidItemException.class, () -> taskListTest1.deleteTask(5));
    }

    @Test
    public void setDone_success() throws InvalidInputException, MissingInputException, InvalidItemException {
        initialiseTasksForTest();
        assertEquals(TODO_DONE, taskListTest1.setDone(1));
        assertEquals(EVENT_DONE, taskListTest1.setDone(2));
        assertEquals(DEADLINE_DONE, taskListTest1.setDone(3));
    }

    @Test
    public void setDone_throwsException() throws InvalidInputException, MissingInputException {
        initialiseTasksForTest();
        assertThrows(InvalidItemException.class, () -> taskListTest1.setDone(5));
    }

    @Test
    public void updateTaskDesc_success() throws InvalidInputException, MissingInputException {
        initialiseTasksForTest();
        assertEquals(TestCase.TODO_UPDATE, taskListTest1.updateTaskDesc(1, TestCase.NEW_TODO_DESCRIPTION));
    }

    @Test
    public void updateTaskTime_success() throws InvalidInputException, MissingInputException {
        initialiseTasksForTest();
        assertEquals(TestCase.EVENT_UPDATE, taskListTest1.updateTaskTime(2, TestCase.NEW_TIME));
    }

    @Test
    public void updateTaskDate_success() throws InvalidInputException, MissingInputException {
        initialiseTasksForTest();
        assertEquals(TestCase.DEADLINE_UPDATE, taskListTest1.updateTaskDate(3, TestCase.NEW_DATE));
    }

}
