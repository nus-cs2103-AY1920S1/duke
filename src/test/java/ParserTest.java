import com.TaskList;
import com.commands.Command;
import com.commands.DoneCommand;
import com.commands.ListCommand;
import com.commands.SubCommand;
import com.exceptions.DukeException;
import com.util.Parser;
import core.Duke;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parse_listCommand_success() throws DukeException {
        //TaskList taskList = new Duke("F:\\CS2103\\duke\\data\\duke.txt").getTaskList();
        TaskList taskList = new Duke().getTaskList();
        Command expectedOutput = new ListCommand();
        Command actualOutput = new Parser().parse("list");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void parse_doneCommand_sucess() throws DukeException {
        //TaskList taskList = new Duke("F:\\CS2103\\duke\\data\\duke.txt").getTaskList();
        TaskList taskList = new Duke().getTaskList();
        Command expectedOutput = new DoneCommand(1);
        Command actualOutput = new Parser().parse("done 1");

        assertEquals(expectedOutput, actualOutput);
    }

    /*@Test
    // Although attributes are the same, may have something to do with how
    // overriden equals() method is defined in Parser class
    void parse_todoInput_success() throws DukeException {
        Command expectedOutput = new SubCommand("deadline", "cs2103 iP",
                "/by", "28/8/2019 2359");
        TaskList taskList = new Duke("F:\\CS2103\\duke\\data\\duke.txt").getTaskList();
        assertEquals(expectedOutput,
                new Parser().parse("deadline cs2103 iP /by 28/8/2019 2359", taskList));

        TaskList taskList = new Duke("F:\\CS2103\\duke\\data\\duke.txt").getTaskList();
        Command expectedOutput = new AddCommand("todo", "finish cs2103 iP tasks", false);
        Command actualOutput = new Parser().parse("todo finish cs2103 iP tasks", taskList);

        // Debug
        System.out.println("***Expected***");
        expectedOutput.print();
        System.out.println("=====");
        System.out.println("***Actual***");
        actualOutput.print();

        assertEquals(expectedOutput, actualOutput);
    }*/

    @Test
    void parse_missingBy_exceptionThrown() {
        Command expectedOutput = new SubCommand("deadline", "cs2103 iP",
                "/by", "28/8/2019 2359");
        //TaskList taskList = new Duke("F:\\CS2103\\duke\\data\\duke.txt").getTaskList();
        TaskList taskList = new Duke().getTaskList();
        try {
            assertEquals(expectedOutput,
                    new Parser().parse("deadline cs2103 iP 28/8/2019 2359"));
        } catch (DukeException e) {
            assertEquals("Please have one \"/by\" provided.", e.getMessage());
        }
    }

    @Test
    void splitStrIntoArr_stringInputPattern_success() {
        ArrayList<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("Kiwis");
        expectedOutput.add("are");
        expectedOutput.add("cute");
        assertEquals(expectedOutput,
                new Parser().splitStrIntoArr("Kiwis are cute", " "));
    }

}
