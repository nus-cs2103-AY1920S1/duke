package main;

import command.AddCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parse_addCommand_noException() throws DukeException {
        String res;
        String fullCommand = "evENT dog's birthday / 26/01/2016 1200";
        try {
            res = Parser.parse(fullCommand).toString();
        } catch (DukeException e) {
            res = e.getMessage();
        }
        assertEquals(new AddCommand("event", "dog's birthday / 26/01/2016 1200").toString(), res);
    }

    @Test
    public void parse_add_Command_withException() {
        String res;
        String fullCommand = "evENT dog's birthday / 32/01/2016 1200";
        try {
            res = Parser.parse(fullCommand).toString();
        } catch (DukeException e) {
            res = e.getMessage();
        }
        assertEquals("Invalid date-time format. Date-time should be in dd/MM/yyyy HHmm format.", res);
    }

    @Test
    public void parse_deleteCommand_noException() throws DukeException {
        String dummyTaskToAdd = "evENT dog's birthday / 26/01/2016 1200";
        Parser.parse(dummyTaskToAdd);

        String res;
        String fullCommand = "delete 5";
        try {
            res = Parser.parse(fullCommand).toString();
        } catch (DukeException e) {
            res = e.getMessage();
        }
        assertEquals("Delete 5", res);

    }

}