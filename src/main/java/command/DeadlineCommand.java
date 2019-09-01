package command;

import run.TaskList;
import run.Ui;
import run.Storage;
import task.Deadline;

public class DeadlineCommand extends AddCommand {
    String rawString;

    public DeadlineCommand(String rawString) {
        this.rawString = rawString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        String remove_command = rawString.replaceFirst("deadline ", "");
        String[] splited = remove_command.split(" /by ");
        Deadline curr_task = new Deadline(splited[0], splited[1]);
        tasks.add(curr_task);
        super.addCommandUpdateState();
    }
}