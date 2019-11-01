import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void makeDeadlineTest(){
        assertEquals("by: 2/12/2019 1800",
                new Deadline("submissions", "by 2/12/2019 1800").makeDeadline("by 2/12/2019 1800"));
    }
}