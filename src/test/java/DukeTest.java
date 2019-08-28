import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void DummyTest() {
        Duke duke = new Duke("duke/duke.txt");
        Assertions.assertEquals(duke.storage.getFilePath(), "duke/duke.txt");
    }
}