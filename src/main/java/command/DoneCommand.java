package command;

import parser.Storage;
import task.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0 || this.index >= tasks.getTasks().size()) {
            Command incorrectCommand = new IncorrectCommand();
            incorrectCommand.execute(tasks, ui, storage);
        } else {
            tasks.getTasks().get(this.index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + tasks.getTasks().get(this.index).getDoneIcon()
                    + "]" + tasks.getTasks().get(this.index).getDescription());
        }
    }
}
