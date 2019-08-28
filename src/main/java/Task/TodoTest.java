package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TestStringConversion(){
        assertEquals("[T][✘] run", new Todo("run").toString());
    }

}
