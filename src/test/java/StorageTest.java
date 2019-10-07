import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void storagetest() {
        String test1 = "\u2713";
        String test2 = "\u2718";
        boolean output1 = Storage.isDone(test1);
        boolean output2 = Storage.isDone(test2);
        assertEquals(true, output1);
        assertEquals(false, output2);
    }
}
