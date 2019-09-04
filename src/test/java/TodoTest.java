import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_toStringTest(){
        String testInput = "test";
        String expectedFileResult = "T | 0 | test\n";
        String expectedPrintResult = "[T][✘]test";
        Todo todoTest = new Todo(testInput);
        assertEquals(expectedFileResult, todoTest.toFileString());
        assertEquals(expectedPrintResult, todoTest.toString());
    }
}
