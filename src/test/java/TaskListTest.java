import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @org.junit.jupiter.api.Test
    void add_userTaskInput_success() throws DukeException {
        assertEquals("[T][\u2718] testing1", new TaskList().add("todo testing1").toString());
        assertEquals("[D][\u2718] testing2 (by: 10th of October 2020, 11:00 am)",
                new TaskList().add("deadline testing2 /by 10/10/2020 1100").toString());
        assertEquals("[E][\u2718] testing3 (at: midnight)",
                new TaskList().add("event testing3 /at midnight").toString());
    }

    @org.junit.jupiter.api.Test
    void add_userTaskInput_exceptionThrown() throws DukeException {
        try {assertEquals("", new TaskList().add("task testing1 /by 10/10/2020 1100").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n    ____________________________________________________________\n", e.getMessage());
        }

        try {assertEquals("", new TaskList().add("event").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! The description of a event cannot be empty." +
                    "\n    ____________________________________________________________\n", e.getMessage());
        }

        try {assertEquals("", new TaskList().add("deadline testing2").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! The time of a deadline cannot be empty." +
                    "\n    ____________________________________________________________\n", e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void done_integerInput_success() {
        assertEquals("[E][\u2713] testing (at: tomorrow)", new TaskList(List.of(new Event("testing", "tomorrow"),
                new Deadline("testing2", "tomorrow"), new ToDo("testing3"))).done(1).toString());
        assertEquals("[T][\u2713] testing3", new TaskList(List.of(new Event("testing", "tomorrow"),
                new Deadline("testing2", "tomorrow"), new ToDo("testing3"))).done(3).toString());
    }

    @org.junit.jupiter.api.Test
    void delete_integerInput_success() {
        List<Task> ls = new ArrayList<>();
        ls.add(new Event("testing", "tomorrow"));
        ls.add(new Deadline("testing2", "tomorrow"));
        ls.add(new ToDo("testing3"));
        assertEquals("[E][\u2718] testing (at: tomorrow)", new TaskList(ls).delete(1).toString());
        assertEquals("[T][\u2718] testing3", new TaskList(ls).delete(2).toString());
    }

    @org.junit.jupiter.api.Test
    void generateInfo() {
        List<Task> ls = new ArrayList<>();
        ls.add(new Event("testing", "tomorrow"));
        ls.add(new Deadline("testing2", "tomorrow"));
        ls.add(new ToDo("testing3"));
        assertEquals("E | 0 | testing | tomorrow\n" +
                "D | 0 | testing2 | tomorrow\n" +
                "T | 0 | testing3",
                new TaskList(ls).generateInfo());
    }
}