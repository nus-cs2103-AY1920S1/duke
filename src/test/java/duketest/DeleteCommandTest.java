import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    @Test
    public void executeTest() throws DukeException {
        Ui uiManager = new Ui(); 
        Storage storeManager = new Storage("StoreTest.sav");
        TaskList taskList = new TaskList();

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
        
        addTodo.execute(uiManager, taskList, storeManager);
        addDeadline.execute(uiManager, taskList, storeManager);   
        addEvent.execute(uiManager, taskList, storeManager);

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