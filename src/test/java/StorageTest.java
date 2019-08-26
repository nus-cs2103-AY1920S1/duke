import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class StorageTest {
    @Test
    public void testLoad() throws DukeException {
        Storage storage = new Storage("C:\\Users\\Lynn\\Desktop\\Y2S1\\CS2103T\\dukenew\\src\\main\\java\\TaskList");
        ArrayList<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("D | [✗] | return books  |  02/12/2019 1900");
        expectedOutput.add("E | [✗] | team meeting  |  02/12/19 1700-1800");
        expectedOutput.add("T | [✗] | buy grocery  |  0/12/19 1600");
        assertEquals(expectedOutput, storage.load());
    }
}
