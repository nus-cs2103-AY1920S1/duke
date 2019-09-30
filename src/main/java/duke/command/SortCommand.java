package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;
import duke.task.TimeTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortCommand extends Command {

    public SortCommand(String[] parts) {
        super(parts);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String type = parts[1];
        if (type.equals("deadline") || type.equals("event")) {
            String response = "Here are your " + type + " sorted chronologically:\n";
            ArrayList<TimeTask> matchTasks = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if (currentTask.getType().equals(type)) {
                    matchTasks.add((TimeTask) currentTask);
                }
            }
            Collections.sort(matchTasks, new TimeTaskDateComparator());
            for (int i = 0; i < matchTasks.size(); i++) {
                int currentItemNumber = i + 1;
                Task currentTask = matchTasks.get(i);
                response += currentItemNumber + "." + currentTask + "\n";
            }
            return response;
        } else {
            throw new DukeException("Sorry that can't be sorted..");
        }
    }

    public class TimeTaskDateComparator implements Comparator<TimeTask> {
        @Override
        public int compare(TimeTask t1, TimeTask t2) {
            if (t1.getLocalDateTime().isBefore(t2.getLocalDateTime())) {
                return -1;
            } else if (t1.getLocalDateTime().isAfter(t2.getLocalDateTime())) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
