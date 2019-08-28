import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    String inputToDo = "todo go for classes";
    String inputEvent = "event classes /at 12pm";
    Parser parser = new Parser();

    @Test
    void parseAction() throws DukeException {
        String actionToDo = parser.parseAction(inputToDo);
        String actionEvent = parser.parseAction(inputEvent);
        assertEquals("todo", actionToDo);
        assertEquals("event", actionEvent);
    }

    @Test
    void parseToDo() {
        String desToDo = parser.parse("todo", inputToDo);
        assertEquals("go for classes", desToDo);
    }

    @Test
    void parseDescription() {
        String des = parser.parseDescription(inputEvent, "event");
        assertEquals("classes", des);
    }

    @Test
    void parseDateTime() {
        String dateTime = parser.parseDateTime(inputEvent, "event");
        assertEquals("12pm", dateTime);
    }
}