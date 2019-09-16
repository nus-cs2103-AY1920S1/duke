package duke.core;

import duke.errors.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import duke.tasks.Task;

import duke.commands.Command;




class TaskListTest {

    @Test
    void getSize_validInput_success() {
        try {
            TaskList tasks = new TaskList(new ArrayList<Task>());
            Command todo = Parser.parseCommand("todo ");
            Command deadline = Parser.parseCommand("deadline task /by 12/12/1212 0013");
            Command event = Parser.parseCommand("event task /at 12/12/1212 0013");
            assertEquals(2, tasks.getSize());
            Command delete = Parser.parseCommand("delete 1");
            assertEquals(1, tasks.getSize());
        } catch (DukeException e) {
                System.out.println(e.getMessage());
                fail();
        }
    }

}