import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void write_wrongCommand() {
        try {
            String input = "todoo Do Project";
            Command c = Parser.parse(input);
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void write_wrongFields() {
        try {
            String input = "deadline do project";
            Command c = Parser.parse(input);
        } catch (Exception e) {
            assertEquals("☹ Incorrect format. Eg. deadline Do Project /by 29/8/2019 1800", e.getMessage());
        }
        try {
            String input = "event do project /at ";
            Command c = Parser.parse(input);
        } catch (Exception e) {
            assertEquals("☹ Incorrect format. Eg. event Talk Show /at 29/8/2019 1800", e.getMessage());
        }
    }
}
