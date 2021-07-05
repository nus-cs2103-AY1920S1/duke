package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test1() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        tl.add(new Todo("abc"));
        assertEquals("Now you have 1 task in the list.\n", ui.printNoOfTaskInList(tl));
    }

    @Test
    public void test2() {
        TaskList tl = new TaskList();
        tl.add(new Todo("abc"));
        tl.add(new Todo("afewhb"));
        tl.add(new Todo("ecbe"));
        Ui ui = new Ui();
        assertEquals("Now you have 3 tasks in the list.\n", ui.printNoOfTaskInList(tl));
    }

    @Test
    public void test3() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeleteCommand("delete     ", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input the task number.\n",
                    e.toString());
        }
    }

    @Test
    public void test4() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeleteCommand("delete", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input the task number.\n",
                    e.toString());
        }
    }

    @Test
    public void test5() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeleteCommand("delete 3", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! You do not have any tasks in your list.\n", e.toString());
        }
    }

    @Test
    public void test6() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        tl.add(new Todo("edcbe"));
        try {
            Parser.checkErrorForDeleteCommand("delete 3", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! You do not have that task in your list. "
                    + "Call 'list' to see all your tasks :-)\n",  e.toString());
        }
    }

    @Test
    public void test7() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadline    ", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of 'deadline' cannot be empty.\n", e.toString());
        }
    }

    @Test
    public void test8() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadline   ", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of 'deadline' cannot be empty.\n", e.toString());
        }
    }

    @Test
    public void test9() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadlinesubmitwork", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input a whitespace between the command 'deadline' "
                    + "and your task description for me to keep track of it correctly :-)\n", e.toString());
        }
    }

    @Test
    public void test10() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadline submit work /bycwpmc", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input a whitespace before and after '/by' for me to "
                    + "keep track of the date/time correctly :-)\n",  e.toString());
        }
    }

    @Test
    public void test11() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadline submit work", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! You would need to schedule a date and time for this "
                    + "deadline using '/by'.\n",  e.toString());
        }
    }

    @Test
    public void test12() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadline /by 13/09/2019 1600", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of 'deadline' cannot be empty.\n",  e.toString());
        }
    }

    @Test
    public void test13() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkErrorForDeadlineCommand("deadline awfbwe /at 13/09/2019 1600", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! You would need to schedule a date and time for this "
                    + "deadline using '/by'.\n",  e.toString());
        }
    }

    @Test
    public void test14() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkMarkDoneError("done 3", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! You do not have that task in your list. "
                    + "Call 'list' to see all your tasks :-)\n",  e.toString());
        }
    }

    @Test
    public void test15() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkMarkDoneError("done", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input the task number.\n",
                    e.toString());
        }
    }

    @Test
    public void test16() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            Parser.checkMarkDoneError("done     ", tl, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please input the task number.\n",
                    e.toString());
        }
    }

}
