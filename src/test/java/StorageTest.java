import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.execution.Storage;

public class StorageTest {

    @Test
    public void countLinesTest() throws IOException {
        String file = "./src/test/test.txt";;
        assertEquals(2, Storage.countLines(file));
    }
}