package seedu.duke;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ParserTest {
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
       try {
           assertEquals("[D][\u2718] abc (by: 24/08/2019 18.00 PM)", Parser.readInFileLine("D | 0 | abc | 24/08/2019 1800").toString());
       } catch (Exception e) {
            assertEquals(1,2);
        }
    }

    @Test
    public void test2() {
        try {
            assertEquals("[D][\u2713] abc (by: 24/08/2019 18.00 PM)", (Parser.readInFileLine("D | 1 | abc | 24/08/2019 1800")).toString());
        } catch (Exception e) {
            assertEquals(1,2);
        }
    }

    @Test
    public void test3() {
        assertEquals(true, Parser.isDeleteCommand("delete 2"));
    }

    @Test
    public void test4() {
        assertEquals(true, Parser.isDeleteCommand("delete ebc"));
    }

    @Test
    public void test5() {
        assertEquals(true, Parser.isTodo("todo efreb"));
    }

    @Test
    public void test6() {
        assertEquals(true, Parser.isTodo("todo"));
    }

    @Test
    public void test7() {
        assertEquals(true, Parser.isEvent("event efreb"));
    }

    @Test
    public void test8() {
        assertEquals(true, Parser.isEvent("event"));
    }

    @Test
    public void test9() {
        assertEquals("[T][\u2718] abcd", Parser.createTodo("todo abcd").toString());
    }

    @Test
    public void test10() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Event event = new Event("abc", date, time);
            assertEquals("[E][\u2718] efgh (at: 24/08/2019 16.00 PM)", Parser.createEvent("event efgh /at 24/08/2019 1600").toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test11() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Event event = new Event("abc", date, time);
            assertEquals("[E][\u2718] help (at: 30/08/2019 16.00 PM)", Parser.createEvent("event help /at 30/08/2019 1600").toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test12() {
        assertEquals(true, Parser.isDeadline("deadline"));
    }

    @Test
    public void test13() {
        assertEquals(true, Parser.isDeadline("deadline vfrfwre brv"));
    }

    @Test
    public void test14() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Deadline dl = new Deadline("abc", date, time);
            assertEquals("[D][\u2718] help (by: 30/08/2019 16.00 PM)", Parser.createDeadline("deadline help /by 30/08/2019 1600").toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test15() {
        assertEquals(12, Parser.taskToMarkDone("done 12"));
    }

    @Test
    public void test16() {
        assertEquals(3, Parser.taskToMarkDone("done 3"));
    }

    @Test
    public void test17() {
        Ui ui = new Ui();
        try {
            assertEquals("EventCommand", Parser.parse("event 3", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test18() {
        Ui ui = new Ui();
        try {
            assertEquals("EventCommand", Parser.parse("event wbcwecb /at revbr", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test19() {
        Ui ui = new Ui();
        try {
            assertEquals("ByeCommand", Parser.parse("bye", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test20() {
        Ui ui = new Ui();
        try {
            assertEquals("DeleteCommand", Parser.parse("delete 99", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test21() {
        Ui ui = new Ui();
        try {
            assertEquals("DeleteCommand", Parser.parse("delete", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test22() {
        Ui ui = new Ui();
        try {
            assertEquals("MarkDoneCommand", Parser.parse("done", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test23() {
        Ui ui = new Ui();
        try {
            assertEquals("DeadlineCommand", Parser.parse("deadline sjcbew c ", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test24() {
        Ui ui = new Ui();
        try {
            assertEquals("TodoCommand", Parser.parse("todo   dw", ui).toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test25() {
        Ui ui = new Ui();
        try {
            Parser.parse("wbfwf", ui);
        } catch (DukeException e) {
            assertEquals("\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n", e.toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test26() {
        Ui ui = new Ui();
        try {
            Parser.parse("wbf", ui);
        } catch (DukeException e) {
            assertEquals("\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n", e.toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }

    @Test
    public void test27() {
        Ui ui = new Ui();
        try {
            Parser.createDeadline("deadline defbf /by 25/08/20191600");
        } catch (Exception e) {
            ui.showParseError();
        }
        assertEquals("\u2639 OOPS!!! Please input the date in dd/mm/yyyy " +
                "and time in 24hr format, separated by a space.\n\r\n", outContent.toString());
    }
}
