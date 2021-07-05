package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    int asciiTick = 0x2713;
    int asciiCross = 0x2718;
    String tick = Character.toString((char)asciiTick);
    String cross = Character.toString((char) asciiCross);

    @Test
    public void test1() {
        try {
            assertEquals("[D][" + cross + "] abc (by: 24 Aug 2019 6.00 PM)",
                    Parser.readInFileLine("D | 0 | abc | 24/08/2019 1800").toString());
        } catch (Exception e) {
            assertEquals(1,2);
        }
    }

    @Test
    public void test2() {
        try {
            assertEquals("[D][" + tick + "] abc (by: 24 Aug 2019 6.00 PM)",
                    (Parser.readInFileLine("D | 1 | abc | 24/08/2019 1800")).toString());
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
        assertEquals(true, Parser.isTodoCommand("todo efreb"));
    }

    @Test
    public void test6() {
        assertEquals(true, Parser.isTodoCommand("todo"));
    }

    @Test
    public void test7() {
        assertEquals(true, Parser.isEventCommand("event efreb"));
    }

    @Test
    public void test8() {
        assertEquals(true, Parser.isEventCommand("event"));
    }

    @Test
    public void test9() {
        assertEquals("[T][" + cross + "] abcd", Parser.createTodo("todo abcd").toString());
    }

    @Test
    public void test10() {
        try {
            assertEquals("[E][" + cross + "] efgh (at: 24 Aug 2019 4.00 PM)",
                    Parser.createEvent("event efgh /at 24/08/2019 1600").toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test11() {
        try {
            assertEquals("[E][" + cross + "] help (at: 30 Aug 2019 4.00 PM)",
                    Parser.createEvent("event help /at 30/08/2019 1600").toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test12() {
        assertEquals(true, Parser.isDeadlineCommand("deadline"));
    }

    @Test
    public void test13() {
        assertEquals(true, Parser.isDeadlineCommand("deadline vfrfwre brv"));
    }

    @Test
    public void test14() {
        try {
            assertEquals("[D][" + cross + "] help (by: 30 Aug 2019 4.00 PM)",
                    Parser.createDeadline("deadline help /by 30/08/2019 1600")
                            .toString());
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
            assertEquals("OOPS!!! I'm sorry but I don't know what that means :-(\n", e.toString());
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
            assertEquals("OOPS!!! I'm sorry but I don't know what that means :-(\n", e.toString());
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
            assertEquals("OOPS!!! Please input the date in dd/mm/yyyy and time in 24hr format "
                    + "or 12hr format as HHmm or H.mmAM or H.mmPM, separated by a space.\n", ui.showParseError());
        }

    }
}
