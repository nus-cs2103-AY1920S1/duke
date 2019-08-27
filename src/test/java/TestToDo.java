import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToDo {
    @Test
    public void testStringConversion(){
       assertEquals("[T][" +  "\u2718" + "] test",new ToDo("test").toString());
    }
}
