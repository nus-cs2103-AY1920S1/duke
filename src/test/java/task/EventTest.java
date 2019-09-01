package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void constructor_noDate_exceptionThrown(){
        try{
            Event event = new Event("Generate Exception");
            fail();
        }catch (DukeException e){
            String expectedOutput = "Incorrect Event format" + System.lineSeparator()
                    + "    Please key in Event (taskname) /by date(d/MM/yyyy) start_time(HHmm)-end_time(HHmm)";
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}
