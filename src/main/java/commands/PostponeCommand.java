package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

public class PostponeCommand extends Command {
    Integer taskNumToPostpone;
    Integer daysToPostpone;
    Integer hoursToPostpone;
    Integer minutesToPostpone;

    public PostponeCommand(String taskNumToPostpone, String daysToPostpone,
                           String hoursToPostpone, String minutesToPostpone) {
        this.taskNumToPostpone = Integer.parseInt(taskNumToPostpone);
        this.daysToPostpone = Integer.parseInt(daysToPostpone);
        this.hoursToPostpone = Integer.parseInt(hoursToPostpone);
        this.minutesToPostpone = Integer.parseInt(minutesToPostpone);
    }

    /**
     * This method is used to mark a task in the list as completed.
     *
     * @return duke's response after postponing a task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            assert tasks.getList()!= null;
            Task t = tasks.getTask(taskNumToPostpone);
            if (t.getSymbol().equals("E")) {
                Event event = (Event) t;
                event.postpone(daysToPostpone, hoursToPostpone, minutesToPostpone);
                storage.updateList(tasks.getList());
                return "Changes were made: \n " + event.toString();
            } else if (t.getSymbol().equals("D")) {
                Deadline deadline = (Deadline) t;
                deadline.postpone(daysToPostpone, hoursToPostpone, minutesToPostpone);
                storage.updateList(tasks.getList());
                return "Changes were made: \n " + deadline;
            } else {
                assert t.getSymbol().equals("T");
                return ui.print("Todo task cannot be postponed!");
            }

        } catch(IndexOutOfBoundsException e) {
            return "Invalid task number :(";
        } catch (Exception e) {
            return e.toString() + "\nInput format should be: postpone taskNumber days hrs mins :) \n " +
                    "Example: postpone 1 0 4 0 will postpone task 1 by 4 hours.";
        }

    }
}
