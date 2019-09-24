package duke.command;

import duke.exception.DukeException;
import duke.handler.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

public class SortCommand extends Command {
    String by;

    public SortCommand(String by) {
        this.by = by;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (by.equals("deadline")) {
            ArrayList<Deadline> deadlines = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    deadlines.add(deadline);
                }
            }
            deadlines = getSortedTaskByDeadline(deadlines);
            if (deadlines.size() == 0) {
                response = ui.NO_DEADLINES_RESPONSE;
            } else {
                StringBuilder sb = new StringBuilder(ui.DEADLINE_SORT_RESPONSE);
                response = ui.listOfDeadlines(deadlines, sb);
            }
        } else if (by.equals("type")) {
            tasks = getSortedTaskByType(tasks);
            if (tasks.size() == 0) {
                response = ui.NO_TASKS_RESPONSE;
            } else {
                StringBuilder sb = new StringBuilder(ui.TYPE_SORT_RESPONSE);
                response = ui.listOfTasks(tasks, sb);
            }
        } else {
            throw new DukeException(ui.DUKE_EXCEPTION);
        }


    }

    public ArrayList<Deadline> getSortedTaskByDeadline(ArrayList<Deadline> deadlineTasks) {
        Collections.sort(deadlineTasks, Deadline.deadlineComparator);
        return deadlineTasks;
    }

    public ArrayList<Task> getSortedTaskByType(ArrayList<Task> tasks) {
        Collections.sort(tasks, Task.typeComparator);
        return tasks;
    }
}
