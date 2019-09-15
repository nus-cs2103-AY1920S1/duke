import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FindCommandTest {
    @Test
    public void executeTest() {
        Ui uiManager = new Ui();
        TaskList taskList = new TaskList();
        Storage storeManager = new Storage("StoreTest.sav");
        Parser parseManager = new Parser();

        Command addTodo = null;
        Command addDeadline = null;
        Command addEvent = null;

        try {
            addTodo = parseManager.parseToCommand("todo This is a Todo");
            addDeadline = parseManager.parseToCommand("deadline This is a deadline /by 02/03/2004 22:22");
            addEvent = parseManager.parseToCommand("event This is an event /at 02/03/2004 22:22");
        } catch (Exception e) {
            // Not suppose to happen
            fail(e.getMessage());
        }

        try {
            addTodo.execute(uiManager, taskList, storeManager);
            addDeadline.execute(uiManager, taskList, storeManager);   
            addEvent.execute(uiManager, taskList, storeManager);
        } catch (Exception e) { 
            // But fail for addCommand failure
            fail();
        }

        FindCommand findThis = new FindCommand("This");
        FindCommand findTodo = new FindCommand("Todo");
        FindCommand findEvent = new FindCommand("Event");
        FindCommand findThat = new FindCommand("That");
        try {
            findThis.execute(uiManager, taskList, storeManager);
            findTodo.execute(uiManager, taskList, storeManager);
            findEvent.execute(uiManager, taskList, storeManager);
            findThat.execute(uiManager, taskList, storeManager);
        } catch (Exception e) {
            fail();
        }
    }

}