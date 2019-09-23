import duke.Parser;
import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void testTodoObject() {
        String myTestName = "Buy SY ferrero rocher";
        Boolean myTestBool = false;
        String testType = "todo";
        Todo todoTestObject = new Todo(myTestName, myTestBool, testType);

        Assertions.assertEquals(todoTestObject.getName(), myTestName, "getName and myTestName does not match!");
        Assertions.assertEquals(todoTestObject.hasDone(), myTestBool, "hasDone() and myTestBool does not match!");
    }

    @Test
    public void testDeadlineObject() {
        String myTestName = "Buy SY ferrero rocher";
        Boolean myTestBool = false;
        String myTestTime = "1/10/2019 1600";
        String testType = "deadline";
        Deadline deadlineTestObject = new Deadline(myTestName, myTestBool, testType, myTestTime, Parser.changeToDateTimeFormat(myTestTime));

        Assertions.assertEquals(deadlineTestObject.getName(), myTestName, "getName and myTestName does not match!");
        Assertions.assertEquals(deadlineTestObject.hasDone(), myTestBool, "hasDone() and myTestBool does not match!");
        Assertions.assertEquals(deadlineTestObject.getLocalDateTime(), Parser.changeToDateTimeFormat(myTestTime),
                "getLocalDateTime() and duke.Parser.changeToDateTimeFormat(myTestTime) does not match!");
    }

    @Test
    public void testEventObject() {
        String myTestName = "Buy SY ferrero rocher";
        Boolean myTestBool = false;
        String myTestTime = "1/10/2019 1600";
        String testType = "deadline";
        Deadline deadlineTestObject = new Deadline(myTestName, myTestBool, testType,myTestTime, Parser.changeToDateTimeFormat(myTestTime));

        Assertions.assertEquals(deadlineTestObject.getName(), myTestName, "getName and myTestName does not match!");
        Assertions.assertEquals(deadlineTestObject.hasDone(), myTestBool, "hasDone() and myTestBool does not match!");
        Assertions.assertEquals(deadlineTestObject.getLocalDateTime(), Parser.changeToDateTimeFormat(myTestTime),
                "getLocalDateTime() and duke.Parser.changeToDateTimeFormat(myTestTime) does not match!");
    }
}
