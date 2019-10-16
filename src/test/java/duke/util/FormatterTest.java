package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.Event;

import java.text.SimpleDateFormat;

public class FormatterTest {
    static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    @Test
    void formatTaskForWriting() throws Exception {
        Task taskA = new ToDo("   make coffee     ");
        Task taskB = new Deadline("   submit due   ", formatter.parse("02/12/2019 1800"));
        Task taskC = new Event("   orbital splashdown   ", formatter.parse("02/12/2019 1800"));
        taskC.toggleState();
        String expectedStringA = "ToDo|0|make coffee";
        String expectedStringB = "Deadline|0|submit due|02/12/2019 1800";
        String expectedStringC = "Event|1|orbital splashdown|02/12/2019 1800";
        String actualStringA = Formatter.formatTaskForWriting(taskA);
        String actualStringB = Formatter.formatTaskForWriting(taskB);
        String actualStringC = Formatter.formatTaskForWriting(taskC);

        assertAll("Ouput",
            () -> assertEquals(expectedStringA, actualStringA),
            () -> assertEquals(expectedStringB, actualStringB),
            () -> assertEquals(expectedStringC, actualStringC)
        );

    }
}
