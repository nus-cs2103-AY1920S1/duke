package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    Storage storage = new Storage("data/duke.txt");
    TaskList taskList = new TaskList(storage);

    @Test
    void createTodo_normalInput_createdSuccessfully() {
        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[T][✗] read book\n"
                    + "Now you have 2 tasks in the list.\n",
                    taskList.createToDo(new String[] {"read book"}));
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void createTodo_wrongInput_exceptionThrown() {
        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[T][✗] read book\n"
                    + "Now you have 2 tasks in the list.\n",
                    taskList.createToDo(new String[]{}));
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.\n", e.toString());
        }
    }

    @Test
    void createDeadline_normalInput_createdSuccessfully() {
        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[D][✗] return book (by: 02/12/2019 1800)\n"
                    + "Now you have 3 tasks in the list.\n",
                    taskList.createDeadline(new String[] {"return book", "/by", "02/12/2019 1800"}));
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void createDeadline_wrongInput_exceptionThrown() {
        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[D][✗] return book (by: 02/12/2019 1800)\n"
                    + "Now you have 3 tasks in the list.\n",
                    taskList.createDeadline(new String[]{}));
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description and due date of a deadline cannot be empty.\n", e.toString());
        }
    }

    @Test
    void createEvent_normalInput_createdSuccessfully() {
        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[E][✗] project meeting (at: 06/08/2019 1400)\n"
                    + "Now you have 1 task in the list.\n",
                    taskList.createEvent(new String[] {"project meeting", "/at", "06/08/2019 1400"}));
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void createEvent_wrongInput_exceptionThrown() {
        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[E][✗] project meeting (at: 06/08/2019 1400)\n"
                    + "Now you have 1 task in the list.\n",
                    taskList.createEvent(new String[]{}));
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description and due date "
                    + "of an event cannot be empty.\n", e.toString());
        }
    }
}
