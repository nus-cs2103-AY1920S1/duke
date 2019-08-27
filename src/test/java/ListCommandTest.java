import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {
    @Test
    public void executeTest() {
        Ui uiManager = new Ui();
        TaskList taskList = new TaskList();
        Storage storeManager = new Storage("StoreTest.sav");

        AddCommand addTodo = new AddCommand(Action.TODO, 
                "This is a Todo");
        AddCommand addDeadline = new AddCommand(Action.DEADLINE, 
                "This is a Deadline /by 02/03/2004 22:22");
        AddCommand addEvent = new AddCommand(Action.EVENT, 
                "This is an Event /at 02/03/2004 22:22");

        try {
            addTodo.execute(uiManager, taskList, storeManager);
            addDeadline.execute(uiManager, taskList, storeManager);   
            addEvent.execute(uiManager, taskList, storeManager);
        } catch (DukeException e) { 
            // But fail for addCommand failure
            fail();
        }

        ListCommand listCommand = new ListCommand();

        try {
            listCommand.execute(uiManager, taskList, storeManager);
        } catch (DukeException e) {
            fail();
        }
    }
}