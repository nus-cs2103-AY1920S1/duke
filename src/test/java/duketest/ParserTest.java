import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseToCommand() {
        Parser parseManager = new Parser();
        try {
            Command list = parseManager.parseToCommand("list");
            Command bye = parseManager.parseToCommand("bye");
            Command done = parseManager.parseToCommand("done 1");
            Command delete = parseManager.parseToCommand("delete 1");
            Command todo = parseManager.parseToCommand("todo This is a todo.");
            Command deadline = parseManager.parseToCommand("deadline This is a Deadline. /by 19:19");
            Command event = parseManager.parseToCommand("event This is an Event. /at 19/01/2021");
            Command find = parseManager.parseToCommand("find A TaSk");
            
            // Check if the command is of the correct type
            if (!(list instanceof ListCommand) 
                    || !(bye instanceof ExitCommand) 
                    || !(done instanceof DoneCommand) 
                    || !(delete instanceof DeleteCommand) 
                    || !(todo instanceof AddCommand) 
                    || !(deadline instanceof AddCommand) 
                    || !(event instanceof AddCommand) 
                    || !(find instanceof FindCommand)) {
                fail();
            }
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseFailToCommand() {
        Parser parseManager = new Parser();
        String[] failTestCases = {"Fail", "todo", "deadline", "event", "done", "delete",
            "Fail Again", "list list", "exti", "help me", "exit again", "Fail Fail Fail"};
        
        for (String tests : failTestCases) {
            try {
                parseManager.parseToCommand(tests);
                fail(tests);
            } catch (DukeException e) {
                // Pass
            } 
        }
    }
}