import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
class BasicTaskCreatorTest {
    public void createTask_test() {
        //cross is \u2718
        try {
            assertEquals("[T][\u2718] borrow book",new BasicTaskCreator().createTask("todo borrow book"));
            assertEquals("[D][\u2718] return book (by: Sunday)",new BasicTaskCreator().createTask("deadline return book /by Sunday"));
            assertEquals("[E][\u2718] project meeting (at: Mon 2-4pm)",new BasicTaskCreator().createTask("event project meeting /at Mon 2-4pm"));
        } catch (OWOException owo) {
            fail();
        }
    }

    public void checkCommand_success() {
        try {
            assertTrue(BasicTaskCreator.checkCommand("todo borrow book", "todo"));
            assertTrue(BasicTaskCreator.checkCommand("deadline return book /by Sunday", "deadline"));
            assertTrue(BasicTaskCreator.checkCommand("event project meeting /at Mon 2-4pm", "event"));
        } catch (OWOException owo) {
            fail();
        }                
    }

    public void checkCommand_exceptionThrown() {
        try {
            BasicTaskCreator.checkCommand("todo", "todo");
        } catch (OWOException owo) {
            assertTrue(true);
        }                
        try {
            BasicTaskCreator.checkCommand("deadline", "deadline");
        } catch (OWOException owo) {
            assertTrue(true);
        }                
        try {
            BasicTaskCreator.checkCommand("event", "event");
        } catch (OWOException owo) {
            assertTrue(true);
        }                
    }

}
