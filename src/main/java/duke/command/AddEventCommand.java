package duke.command;

import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.EventTask;
import duke.task.TaskList;

import java.text.ParseException;

public class AddEventCommand extends AddCommand {
    private String[] details;

    public AddEventCommand(String input) throws ArrayIndexOutOfBoundsException {
        super(input);
        details = Parser.getDetails(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ParseException {
        ui.showLine();
        taskList.add(new EventTask(details[0].trim(), Parser.getAsDate(details[1])));
        ui.out("Got it. I've added this task:");
        ui.out(taskList.get(taskList.size() - 1).toString());
        ui.out("Now you have " + taskList.size() + " tasks in the list.");
        ui.showLine();
    }
}
