import error.UnknownCommandException;
import error.task.InvalidArgumentsException;
import error.task.UnknownDateTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.tasks.Deadline;


public class DeadlineTest {
    @Test
    void taskMessageTest() {
        try {
            Deadline tester = new Deadline("test12345 /by 12/12/2020 0010");
            String taskMessage = tester.getTaskMessage();

            Assertions.assertEquals("[D][\u2718] test12345 (by: Dec 12 2020, Sat, 00:10AM)", taskMessage,
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
            Deadline tester = new Deadline("test12345 /by 12/12/2020 0010");
            tester.setDone(true);
            String taskMessage = tester.getTaskMessage();

            Assertions.assertEquals("[D][\u2713] test12345 (by: Dec 12 2020, Sat, 00:10AM)", taskMessage,
                    "Event not set to done!");
        } catch (UnknownDateTimeException e) {
            Assertions.fail("UnknownDateTimeException caught!");
        } catch (InvalidArgumentsException e) {
            Assertions.fail("InvalidArgumentsException caught!");
        }
    }

    @Test
    void deadlineShouldAcceptValidDate() {
        Assertions.assertThrows(UnknownDateTimeException.class,
                () -> new Deadline("test1235 /by 12391204asd"));
    }
}
