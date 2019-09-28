import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {
    @Test
    public void creatingGoodTodoTask() {
        try {
            // test case 1
            Task t = Task.create("todo abc");
            assertEquals(t.toString(), "[T]" + "[" + (char) 0x2718 + "] " + "abc");

            // test case 2
            t.setDone();
            assertEquals(t.toString(), "[T]" + "[" + (char) 0x2713 + "] " + "abc");
        } catch (IceBearException e) {
            fail("Exception Occurred.\n" + e.toString());
        }
    }

    @Test
    public void creatingGoodEventTask() {
        try {
            Task t = Task.create("event hello /at 01/01/2011 1500");
            assertEquals("[E]" + "[" + (char) 0x2718 + "] " + "hello"
                    + " (at: 1st of January 2011, 3:00pm)", t.toString());

            t.setDone();
            assertEquals("[E]" + "[" + (char) 0x2713 + "] " + "hello"
                    + " (at: 1st of January 2011, 3:00pm)", t.toString());
        } catch (IceBearException e) {
            fail("Exception Occurred.\n" + e.toString());
        }
    }

    @Test
    public void creatingFailEventTask() {
        try {
            Task t1 = Task.create("event hello /at 01/012011 1500");
            fail("1st Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t2 = Task.create("event /at 01/01/2011 1500");
            fail("2nd Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t3 = Task.create("event hello /at 01/13/2011 1500");
            fail("3rd Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t4 = Task.create("event hello /at 32/01/2011 1500");
            fail("4th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t5 = Task.create("event hello /at 31/02/2011 1500");
            fail("5th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t6 = Task.create("event hello /at 01/13/2011 1500");
            fail("6th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t7 = Task.create("event hello /at 01/12/211 1500");
            fail("7th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void creatingGoodDeadlineTask() {
        try {
            Task t = Task.create("deadline hello /by 01/01/2011 1500");
            assertEquals("[D]" + "[" + (char) 0x2718 + "] " + "hello"
                    + " (by: 1st of January 2011, 3:00pm)", t.toString());

            t.setDone();
            assertEquals("[D]" + "[" + (char) 0x2713 + "] " + "hello"
                    + " (by: 1st of January 2011, 3:00pm)", t.toString());
        } catch (IceBearException e) {
            fail("Exception Occurred.\n" + e.toString());
        }
    }

    @Test
    public void creatingFailDeadlineTask() {
        try {
            Task t1 = Task.create("deadline hello /by 01/012011 1500");
            fail("1st Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t2 = Task.create("deadline /by 01/01/2011 1500");
            fail("2nd Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t3 = Task.create("deadline hello /by 01/13/2011 1500");
            fail("3rd Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t4 = Task.create("deadline hello /by 32/01/2011 1500");
            fail("4th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t5 = Task.create("deadline hello /by 31/02/2011 1500");
            fail("5th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t6 = Task.create("deadline hello /by 01/13/2011 1500");
            fail("6th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
        try {
            Task t7 = Task.create("deadline hello /by 01/12/211 1500");
            fail("7th Exception was not thrown");
        } catch (IceBearException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readingFromFile() {
        try {
            Task t1 = Task.createFromFile("T | 0 | join sports club");
            assertEquals("[T][" + (char) 0x2718 + "] join sports club", t1.toString());

            Task t2 = Task.createFromFile("T | 1 | join sports club");
            assertEquals("[T][" + (char) 0x2713 + "] join sports club", t2.toString());

            Task t3 = Task.createFromFile("D | 0 | don't die | 22/01/2019 2234");
            assertEquals("[D][" + (char) 0x2718
                    + "] don't die (by: 22nd of January 2019, 10:34pm)", t3.toString());

            Task t4 = Task.createFromFile("E | 1 | don't die | 22/11/2019 2234");
            assertEquals("[E][" + (char) 0x2713
                    + "] don't die (at: 22nd of November 2019, 10:34pm)", t4.toString());
        } catch (IceBearException e) {
            fail(e.toString());
        }
    }

    @Test
    public void writingToFile() {
        try {
            Task t1 = Task.create("event hello /at 01/01/2011 1500");
            assertEquals("E | 0 | hello | 1/1/2011 1500", t1.toFileString());

            Task t2 = Task.create("deadline hello /by 01/01/2011 1500");
            assertEquals("D | 0 | hello | 1/1/2011 1500", t2.toFileString());

            Task t3 = Task.create("todo hello");
            assertEquals("T | 0 | hello", t3.toFileString());
        } catch (IceBearException e) {
            fail(e.toString());
        }
    }
}
