import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import duke.task.*;
import duke.execution.DukeException;

public class DeadlineTest {
    @Test
    public void deadline_toStringTest(){
        String testInput_1 = "test";
        String testInput_2 = "2/9/2019 2000";
        String expectedFileResult = "D | 0 | test | 2/9/2019 2000";
        String expectedPrintResult = "[D][✘]test (by: 2/9/2019 2000)";
        try {
            Deadline deadlineTest = new Deadline(testInput_1, testInput_2);
            assertEquals(expectedFileResult, deadlineTest.toFileString());
            assertEquals(expectedPrintResult, deadlineTest.toString());
        }catch (DukeException e){
            fail("DukeException thrown: " + e.getMessage());
        }
    }
}
