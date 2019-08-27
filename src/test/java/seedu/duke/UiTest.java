package seedu.duke;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test1() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        tl.add(new Todo("abc"));
        ui.printNoOfTaskInList(tl);
        assertEquals("Now you have 1 task in the list.\n\r\n", outContent.toString());
    }

    @Test
    public void test2() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        tl.add(new Todo("abc"));
        tl.add(new Todo("afewhb"));
        tl.add(new Todo("ecbe"));
        ui.printNoOfTaskInList(tl);
        assertEquals("Now you have 3 tasks in the list.\n\r\n", outContent.toString());
    }

    @Test
    public void test3() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeleteCommand("delete     ", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! Please input the task number you would like to delete.\n",
                    e.toString());
        }
    }

    @Test
    public void test4() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeleteCommand("delete", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! Please input the task number you would like to delete.\n",
                    e.toString());
        }
    }

    @Test
    public void test5() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeleteCommand("delete 3", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! You do not have any tasks in your list.\n", e.toString());
        }
    }

    @Test
    public void test6() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        tl.add(new Todo("edcbe")) ;
        try {
            ui.checkErrorForDeleteCommand("delete 3", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! You do not have that task in your list. "
                    + "Call 'list' to see all your tasks :-)\n",  e.toString());
        }
    }

    @Test
    public void test7() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadline    ", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! The description of deadline cannot be empty.\n", e.toString());
        }
    }

    @Test
    public void test8() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadline   ", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! The description of deadline cannot be empty.\n", e.toString());
        }
    }

    @Test
    public void test9() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadlinesubmitwork", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! Please input a whitespace between the command 'deadline' "
                    + "and your task description for me to keep track of it correctly :-)\n", e.toString());
        }
    }

    @Test
    public void test10() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadline submit work /bycwpmc", tl);
        } catch (DukeException e ) {
            assertEquals("\u2639 OOPS!!! Please input a whitespace before and after '/by' for me to "
                    + "keep track of the date/time correctly :-)\n",  e.toString());
        }
    }

    @Test
    public void test11() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadline submit work", tl);
        } catch (DukeException e ) {
            assertEquals( "\u2639 OOPS!!! You would need to schedule a date/time for this "+
                    "deadline using '/by'.\n",  e.toString());
        }
    }

    @Test
    public void test12() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadline /by 13/09/2019 1600", tl);
        } catch (DukeException e ) {
            assertEquals( "\u2639 OOPS!!! The description of deadline cannot be empty.\n",  e.toString());
        }
    }

    @Test
    public void test13() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkErrorForDeadlineCommand("deadline awfbwe /at 13/09/2019 1600", tl);
        } catch (DukeException e ) {
            assertEquals( "\u2639 OOPS!!! You would need to schedule a date/time for this " +
                    "deadline using '/by'.\n",  e.toString());
        }
    }

    @Test
    public void test14() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkMarkDoneError("done 3", tl);
        } catch (DukeException e ) {
            assertEquals( "\u2639 OOPS!!! You do not have that task in your list. " +
                    "Call 'list' to see all your tasks :-)\n",  e.toString());
        }
    }

    @Test
    public void test15() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkMarkDoneError("done", tl);
        } catch (DukeException e ) {
            assertEquals( "\u2639 OOPS!!! Please input the task number you would like to mark as done.\n",
                    e.toString());
        }
    }

    @Test
    public void test16() {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        try {
            ui.checkMarkDoneError("done     ", tl);
        } catch (DukeException e ) {
            assertEquals( "\u2639 OOPS!!! Please input the task number you would like to mark as done.\n",
                    e.toString());
        }
    }

}
