import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * TaskListTest class used to test the functionality of TaskList.
 */
public class TaskListTest {

    /**
     * Creates an ArrayList with the provided task.
     * @param task Task that the ArrayList will be created with.
     * @return ArrayList of the task provided.
     */
    public ArrayList<Task> createList(Task task) {
        ArrayList<Task> newList = new ArrayList<>();
        newList.add(task);
        return newList;
    }

    /**
     * Tests the success of adding Todo / Deadline / Event tasks to a TaskList.
     * @throws DukeException Exception is raised when an invalid task is added.
     */
    @Test
    public void add_tasks_success() throws DukeException {
        Task todoStub = new Todo("Test String");
        ArrayList<Task> testTodoList = createList(todoStub);
        TaskList todoTaskList = new TaskList();
        todoTaskList.add(todoStub);
        assertEquals(testTodoList, todoTaskList.getTasks());

        Task deadlineStub = new Deadline("Test String", dateStub(), timeStub());
        ArrayList<Task> testDeadlineList = createList(deadlineStub);
        TaskList deadlineTaskList = new TaskList();
        deadlineTaskList.add(deadlineStub);
        assertEquals(testDeadlineList, deadlineTaskList.getTasks());

        Task eventStub = new Event("Test String", dateStub(), timeStub(), timeStub());
        ArrayList<Task> testEventList = createList(eventStub);
        TaskList eventTaskList = new TaskList();
        eventTaskList.add(eventStub);
        assertEquals(testEventList, eventTaskList.getTasks());
    }

    /**
     * Generates a LocalDate constant which can be used for testing.
     * @return LocalDate stub used for testing.
     */
    public static LocalDate dateStub() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        return LocalDate.parse("26/11/1996", formatter);
    }

    /**
     * Generates a LocalTime constant which can be used for testing.
     * @return LocalTime stub used for testing.
     */
    public static LocalTime timeStub() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);
        return LocalTime.parse("1234", formatter);
    }

}
