import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    @Test
    public void executeTest() {
        Ui uiManager = new Ui(); 
        Storage storeManager = new Storage("StoreTest.sav");
        TaskList taskList = new TaskList();

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

        // Actual Test
        DeleteCommand deleteThird = new DeleteCommand(3);
        DeleteCommand deleteFirst = new DeleteCommand(1);
        
        try {
            deleteThird.execute(uiManager, taskList, storeManager);
            deleteFirst.execute(uiManager, taskList, storeManager);
            // Execute again to delete Todo.
            deleteFirst.execute(uiManager, taskList, storeManager);
        } catch (DukeException e) {
            fail();
        }

        try {
            deleteFirst.execute(uiManager, taskList, storeManager);
            fail();
        } catch (DukeException e) {
            // Pass
        }

    }

}