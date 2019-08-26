import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
//    @Test
//    public void dummyFailTest() {
//        assertEquals("lalala", "placeholder");
//    }

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void dummyPassTest() {
        assertEquals("lalala", "lalala");
    }
    @Test
    void uiHelloTest() {
        new Ui().printHello();
        assertEquals("Hello! I'm Duke What can I do for you?\r\n", outContent.toString());
    }

    @Test
    void deadlineGetStatusTest() {
        Deadline d = new Deadline("123", "2", "14 2 2");
        assertEquals("[D][\u2718] 123 (2: 14 2 2)", d.getStatus());
    }
    @Test
    void todoGetStatusTest() {
        ToDo t = new ToDo("12n2nl2");
        assertEquals("[T][\u2718] 12n2nl2", t.getStatus());
    }
    @Test
    void taskIsDoneTest() {
        Task t = new Task("desc");
        t.isDone = true;
        assertEquals("[\u2713] desc", t.getStatus());
    }
}
