import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void Test() throws Exception {

        Deadline deadlinetask = new Deadline("finish homework /by 5/5/2019 2359", true);

        assertEquals("[D][✘] finish homework (by: 5th of May 2019, 1159pm)", deadlinetask.toString());
    }
}