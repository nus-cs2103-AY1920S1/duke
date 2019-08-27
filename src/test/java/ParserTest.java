import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseToCommand() {
        Ui uiManager = new Ui();
        Parser parseManager = new Parser();
        try {
            Command list = parseManager.parseToCommand("list", uiManager);
            Command bye = parseManager.parseToCommand("bye", uiManager);
            Command done = parseManager.parseToCommand("done 1", uiManager);
            Command delete = parseManager.parseToCommand("delete 1", uiManager);
            Command todo = parseManager.parseToCommand("todo This is a todo.", uiManager);
            Command deadline = parseManager.parseToCommand(
                    "deadline This is a Deadline. 19:99", uiManager);
            Command event = parseManager.parseToCommand(
                    "event This is an Event. 19/01/2021", uiManager);
            
            // Check if the command is of the correct type
            if(!(list instanceof ListCommand) ||
               !(bye instanceof ExitCommand) ||
               !(done instanceof DoneCommand) ||
               !(delete instanceof DeleteCommand) ||
               !(todo instanceof AddCommand) ||
               !(deadline instanceof AddCommand) ||
               !(event instanceof AddCommand)) {
                fail();
            }
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseFailToCommand() {
        Ui uiManager = new Ui();
        Parser parseManager = new Parser();
        String[] failTestCases = {"Fail", "todo", "deadline", "event", "done", "delete",
            "Fail Again", "list list", "exti", "help me", "exit again", "Fail Fail Fail"};
        
        for(String tests : failTestCases) {
            try {
                parseManager.parseToCommand(tests, uiManager);
                fail(tests);
            } catch (DukeException e) {
                // Pass
            } 
        }
    }
}