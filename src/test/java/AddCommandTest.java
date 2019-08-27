import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    private final Ui uiManager = new Ui(); 
    private final Storage storeManager = new Storage("StoreTest.sav");
    private final TaskList taskList = new TaskList();
    
    @Test
    public void executeTest() {
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
            fail();
        }

    }

    @Test
    public void executeFailTest() {
        AddCommand addDeadline = new AddCommand(Action.DEADLINE, 
                "This is a Deadline /by 02/03/2004 99:99");
        AddCommand addEvent = new AddCommand(Action.EVENT, 
                "This is an Event /at 02-03-2004 22:22");
        
        try {
            addDeadline.execute(uiManager, taskList, storeManager);
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            addEvent.execute(uiManager, taskList, storeManager);
            fail();
        } catch (DukeException e) {
            // Pass
        }
    }

}