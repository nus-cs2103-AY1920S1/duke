import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    private final Ui uiManager = new Ui(); 
    private final Storage storeManager = new Storage("StoreTest.sav");
    private final TaskList taskList = new TaskList();
    
    @Test
    public void executeTest() throws DukeException {
        AddCommand addTodo;
        AddCommand addDeadline;
        AddCommand addEvent;

        try {
            addTodo = new AddCommand(new Todo("This is a Todo"));
            addDeadline = new AddCommand(new Deadline("This is a Deadline", "02/03/2004 22:22"));
            addEvent = new AddCommand(new Event("This is an Event", "02/03/2004 22:22"));
        } catch (DukeException e) {
            throw new DukeException("The test itself have an error");
        }

        try {
            addTodo.execute(uiManager, taskList, storeManager);
            addDeadline.execute(uiManager, taskList, storeManager);   
            addEvent.execute(uiManager, taskList, storeManager);
        } catch (DukeException e) { 
            fail();
        }

    }

}