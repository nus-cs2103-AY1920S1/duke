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
                response = "You have no deadlines.";
            } else {
                StringBuilder sb = new StringBuilder("Here are your tasks sorted by " + by + ":\n");
                for (int i = 0; i < deadlines.size(); i++) {
                    int k = i + 1;
                    if (i > 0) {
                        sb.append("\n");
                    }
                    sb.append(k);
                    sb.append(". ");
                    sb.append(deadlines.get(i));
                }
                response = sb.toString();
            }
        } else if (by.equals("type")) {
            tasks = getSortedTaskByType(tasks);
            if (tasks.size() == 0) {
                response = "You have no tasks.";
            } else {
                StringBuilder sb = new StringBuilder("Here are your tasks sorted by " + by
                        + ":\nDeadline, Event, Todo\n");
                for (int i = 0; i < tasks.size(); i++) {
                    int k = i + 1;
                    if (i > 0) {
                        sb.append("\n");
                    }
                    sb.append(k);
                    sb.append(". ");
                    sb.append(tasks.get(i));
                }
                response = sb.toString();
            }
        } else {
            throw new DukeException("I don't know what that means!");
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
