package duke.ui;

import duke.core.TaskList;
import duke.tasks.Task;

import java.util.List;

public interface UiInterface {
    void greet(boolean taskListExists, boolean storageInitialised);

    void echoList(TaskList taskList, List<String> archives);

    void echoMatchingTasks(List<Task> matchingTasks);

    void echoAddedTask(Task taskToAdd, int taskListSize);

    void echoCompletedTask(Task taskToComplete);

    void echoDeletedTask(Task taskToDelete, int taskListSize);

    void echoException(Exception e);

    void echoDukeMessage(String msg);

    void exit();
}
