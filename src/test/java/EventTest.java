import error.UnknownCommandException;
import error.task.InvalidArgumentsException;
import error.task.UnknownDateTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.tasks.Event;

public class EventTest {
    @Test
    void taskMessageTest() {
        try {
            Event tester = new Event("test12345 /at 12/12/2020 0010");
            String taskMessage = tester.getTaskMessage();

            Assertions.assertEquals("[E][\u2718] test12345 (at: Dec 12 2020, Sat, 00:10AM)", taskMessage,
                    "Incorrect event task message!");
        } catch (UnknownDateTimeException e) {
            Assertions.fail("UnknownDateTimeException caught!");
        } catch (InvalidArgumentsException e) {
            Assertions.fail("InvalidArgumentsException caught!");
        }
    }

    @Test
    void taskSetDoneTest() {
        try {
            Event tester = new Event("test12345 /at 12/12/2020 0010");
            tester.setDone(true);
            String taskMessage = tester.getTaskMessage();

            Assertions.assertEquals("[E][\u2713] test12345 (at: Dec 12 2020, Sat, 00:10AM)", taskMessage,
                    "Event not set to done!");
        } catch (UnknownDateTimeException e) {
            Assertions.fail("UnknownDateTimeException caught!");
        } catch (InvalidArgumentsException e) {
            Assertions.fail("InvalidArgumentsException caught!");
        }
    }

    @Test
    void eventShouldAcceptValidDate() {
        Assertions.assertThrows(UnknownDateTimeException.class,
                () -> new Event("test1235 /at 12391204asd"));
    }
}
