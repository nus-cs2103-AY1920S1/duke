import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        Todo test = new Todo("Read book");
        assertEquals("[T][" + "✘" + "] Read book", test.toString());
        assertEquals("T | " + "✘" + " | Read book", test.stringForAppend());
    }
}