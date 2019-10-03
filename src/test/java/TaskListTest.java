import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @org.junit.jupiter.api.Test
    void add_userTaskInput_success() throws DukeException {
        assertEquals("[T][\u2718] check1", new TaskList().add("todo check1").toString());
        assertEquals("[D][\u2718] check2 (by: 25th of September 2019, 09:00 am)",
                new TaskList().add("deadline check2 /by 25/9/2019 0900").toString());
        assertEquals("[E][\u2718] check3 (at: today)",
                new TaskList().add("event check3 /at today").toString());
    }

    /*@org.junit.jupiter.api.Test
    void add_userTaskInput_exceptionThrown() throws DukeException {
        try {assertEquals("", new TaskList().add("task check1 /by 25/9/2019 0900").toString());
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

        try {assertEquals("", new TaskList().add("deadline check2").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! The time of a deadline cannot be empty." +
                    "\n    ____________________________________________________________\n", e.getMessage());
        }
    }
    */
    @org.junit.jupiter.api.Test
    void done_integerInput_success() {
        assertEquals("[E][\u2713] check1 (at: today)", new TaskList(List.of(new Event("check1", "today"),
                new Deadline("check2", "tomorrow"), new ToDo("check3"))).done(1).toString());
        assertEquals("[T][\u2713] check3", new TaskList(List.of(new Event("check1", "today"),
                new Deadline("check2", "tomorrow"), new ToDo("check3"))).done(3).toString());
    }

    @org.junit.jupiter.api.Test
    void delete_integerInput_success() {
        List<Task> ls = new ArrayList<>();
        ls.add(new Event("check1", "tomorrow"));
        ls.add(new Deadline("check2", "tomorrow"));
        ls.add(new ToDo("check3"));
        assertEquals("[E][\u2718] check1 (at: tomorrow)", new TaskList(ls).delete(1).toString());
        assertEquals("[T][\u2718] check3", new TaskList(ls).delete(2).toString());
    }

    /*
    @org.junit.jupiter.api.Test
    void genInfo() {
        List<Task> ls = new ArrayList<>();
        ls.add(new Event("testing", "tomorrow"));
        ls.add(new Deadline("testing2", "tomorrow"));
        ls.add(new ToDo("testing3"));
        assertEquals("E | 0 | testing | tomorrow\n" +
                        "D | 0 | testing2 | tomorrow\n" +
                        "T | 0 | testing3",
                new TaskList(ls).genInfo());
    }
    */
}