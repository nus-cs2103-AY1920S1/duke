package com.commands;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand extends AddCommand {

    public ToDoCommand(String taskDescription) {
        super("todo", taskDescription, false);
    }

    /**
     * Adds ToDo task to list, saves new list to text file.
     * @param duke
     * @throws IOException
     */
    public void execute(Duke duke) throws IOException {
        TaskList taskList = duke.getTaskList();
        Storage storage = duke.getStorage();
        Ui ui = duke.getUi();

        Task newTask = new ToDo(description);
        taskList.addTask(newTask);
        ArrayList<Task> taskArr = taskList.getTaskArr();
        storage.save(taskArr);
        ui.showAddTaskResponse(newTask, taskArr);
    }

}
