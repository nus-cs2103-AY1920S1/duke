import error.task.EmptyTodoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import task.tasks.ToDo;

public class ToDoTest {
    @Test
    void taskMessageTest() {
        try {
            ToDo tester = new ToDo("test12345");
            String taskMessage = tester.getTaskMessage();

            Assertions.assertEquals("[T][\u2718] test12345", taskMessage, "Incorrect todo task message!");
        } catch (EmptyTodoException e) {
            Assertions.fail("EmptyTodoException caught!");
        }
    }

    @Test
    void taskSetDoneTest() {
        try {
            ToDo tester = new ToDo("test12345");
            tester.setDone(true);
            String taskMessage = tester.getTaskMessage();

            Assertions.assertEquals("[T][\u2713] test12345", taskMessage, "ToDo not set to done!");
        } catch (EmptyTodoException e) {
            Assertions.fail("EmptyTodoException caught!");
        }
    }

    @Test
    void todoShouldNotAcceptEmptyArgument() throws EmptyTodoException {
        Assertions.assertThrows(EmptyTodoException.class, () -> new ToDo(""));
    }
}
