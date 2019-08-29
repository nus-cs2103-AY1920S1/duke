import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void testTodoObject() {
        String myTestName = "Buy SY ferrero rocher";
        Boolean myTestBool = false;
        Todo todoTestObject = new Todo(myTestName, myTestBool);

        Assertions.assertEquals(todoTestObject.getName(), myTestName, "getName and myTestName does not match!");
        Assertions.assertEquals(todoTestObject.hasDone(), myTestBool, "hasDone() and myTestBool does not match!");
    }

    @Test
    public void testDeadlineObject() {
        String myTestName = "Buy SY ferrero rocher";
        Boolean myTestBool = false;
        String myTestTime = "1/10/2019 1600";
        Deadline deadlineTestObject = new Deadline(myTestName, myTestBool, myTestTime, Parser.changeToDateTimeFormat(myTestTime));

        Assertions.assertEquals(deadlineTestObject.getName(), myTestName, "getName and myTestName does not match!");
        Assertions.assertEquals(deadlineTestObject.hasDone(), myTestBool, "hasDone() and myTestBool does not match!");
        Assertions.assertEquals(deadlineTestObject.getTime(), myTestTime, "getTime() and myTestTime does not match!");
        Assertions.assertEquals(deadlineTestObject.getLocalDateTime(), Parser.changeToDateTimeFormat(myTestTime),
                "getLocalDateTime() and Parser.changeToDateTimeFormat(myTestTime) does not match!");
    }

    @Test
    public void testEventObject() {
        String myTestName = "Buy SY ferrero rocher";
        Boolean myTestBool = false;
        String myTestTime = "1/10/2019 1600";
        Deadline deadlineTestObject = new Deadline(myTestName, myTestBool, myTestTime, Parser.changeToDateTimeFormat(myTestTime));

        Assertions.assertEquals(deadlineTestObject.getName(), myTestName, "getName and myTestName does not match!");
        Assertions.assertEquals(deadlineTestObject.hasDone(), myTestBool, "hasDone() and myTestBool does not match!");
        Assertions.assertEquals(deadlineTestObject.getTime(), myTestTime, "getTime() and myTestTime does not match!");
        Assertions.assertEquals(deadlineTestObject.getLocalDateTime(), Parser.changeToDateTimeFormat(myTestTime),
                "getLocalDateTime() and Parser.changeToDateTimeFormat(myTestTime) does not match!");
    }
}
