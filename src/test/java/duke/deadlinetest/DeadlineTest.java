package duke.deadlinetest;

import duke.tasklist.Deadline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setup() {
        deadline = new Deadline("Buy Christmas Presents", "25/12/2019 1700");
    }


    @Test
    public void toStringCorrectly() {
        Assertions.assertEquals(
                deadline.toString(),
                "[D][✗] Buy Christmas Presents (by: 25/12/2019 1700)\n"
        );
    }
}
