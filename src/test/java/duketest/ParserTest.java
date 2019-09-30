package duketest;

import command.AddCommand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.Parser;
import main.DukeException;

public class ParserTest {

    @Test
    /**
     * Checks if Parser.parse executes the command deadline correctly.
     */
    public void parseTest1() {
        ArrayList<String> args = new ArrayList<>();
        args.add("CS2103 Project");
        args.add("12/08/2019");
        String res;
        try {
            res = Parser.parse("deadline CS2103 Project /by 12/08/2019").toString();
        } catch (DukeException e) {
            res = e.getMessage();
        }
        assertEquals(res,
                new AddCommand("deadline", args).toString());
    }

    @Test
    /**
     * Checks if Parser.parse executes an unknown command correctly.
     */
    public void parseTest2() {
        String res = "";
        try {
            res = Parser.parse("unknownCommand").toString();
        } catch (DukeException e) {
            res = e.getMessage();
        }
        assertEquals(res,
                "Sorry! I don't know what this command does: unknownCommand");
    }

}
