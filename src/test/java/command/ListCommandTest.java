package command;

import org.junit.jupiter.api.Test;
import storage.FakeStorage;
import task.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    @Test
    void execute() {
        assertEquals("Here are the tasks in your list:\n",
                new ListCommand().execute(new TaskList(), new Ui(), new FakeStorage()));
    }
}