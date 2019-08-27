import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void dummyTest() throws ParseException {
        assertEquals("[D][✘] submit project (by: 02/12/2019 1700)", new Deadline("submit project", "02/12/2019 1700").toString());
    }
}
