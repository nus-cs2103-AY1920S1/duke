package duke.command;

import duke.task.Ui;

/**
 * The ListCommand class defines the behaviour of a list command.
 * 
 * @author Joel Loong
 */
public class ListCommand extends Command {
    @Override
    public String execute() {
        return Ui.printList();
    }
}