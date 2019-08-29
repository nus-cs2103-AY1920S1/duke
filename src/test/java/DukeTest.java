import org.junit.jupiter.api.Test;

import java.text.ParseException;


public class DukeTest {
    @Test
    public void dukeTest() throws ParseException {
        DeadlineTest.deadlineTest();
        EventTest.eventTest();
        TodoTest.todoTest();
    }

}
