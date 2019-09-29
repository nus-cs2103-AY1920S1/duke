package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import static duke.util.ObjectsForTest.DEADLINE2;
import static duke.util.ObjectsForTest.EVENT;
import static duke.util.ObjectsForTest.FILE_PATH;
import static duke.util.ObjectsForTest.TASK_LIST_ALL;
import static duke.util.ObjectsForTest.TASK_LIST_TODO;
import static duke.util.ObjectsForTest.TODO1;
import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {

    private Storage storage = new Storage(FILE_PATH);
    private Ui ui = new Ui();

    @Test
    void execute_addTodo_success() {
        TaskList taskList = new TaskList();
        AddCommand addTodo = new AddCommand(TODO1);
        String executeResult = addTodo.execute(taskList, ui, storage);
        assertEquals(ui.showTaskAdded(1, TODO1), executeResult);
        assertEquals(TASK_LIST_TODO, taskList);
    }

    @Test
    void execute_addDeadline_success() {
        TaskList taskList = new TaskList();
        AddCommand addTodo = new AddCommand(TODO1);
        String executeResult = addTodo.execute(taskList, ui, storage);
        assertEquals(ui.showTaskAdded(1, TODO1), executeResult);
        assertEquals(TASK_LIST_TODO, taskList);
    }

    @Test
    void execute_addEvent_success() {
        TaskList taskList = new TaskList();
        AddCommand addTodo = new AddCommand(TODO1);
        String executeResult = addTodo.execute(taskList, ui, storage);
        assertEquals(ui.showTaskAdded(1, TODO1), executeResult);
        assertEquals(TASK_LIST_TODO, taskList);
    }

    @Test
    void execute_addSeveralTasks_success() {
        TaskList taskList = new TaskList();
        new AddCommand(TODO1).execute(taskList, ui, storage);
        new AddCommand(DEADLINE2).execute(taskList, ui, storage);
        String executeResult = new AddCommand(EVENT).execute(taskList, ui, storage);
        assertEquals(ui.showTaskAdded(3, EVENT), executeResult);
        assertEquals(TASK_LIST_ALL, taskList);
    }

}