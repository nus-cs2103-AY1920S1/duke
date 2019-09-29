package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.test

public class TaskTest {
    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[T][✗]  eat", new Todo("eat").toString());
        Assertions.assertEquals("[D][✗]  read by: 6th September 2019, 740pm", new Deadline("read", "06/09/2019 1940"));
        Assertions.assertEquals("[D][✗]  meeting Jason at: 6th September 2019, 8:02pm",
                new Event("meeting Jason", "06/09/2019 2002"));
    }
    public void testSetDone() {
        Task temp = new Todo("sleep");
        temp.setDone();
        Assertions.assertEquals(done, temp.isDone());
    }
}