package duke.command;

import duke.DukeException;
import duke.Model;
import duke.io.UiOutput;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.ArgumentParser;
import duke.Storage;

import java.util.Map;

/**
 * When executed, adds a new deadline to the <code>TaskList</code>.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(String[] args) {
        super(args);

        argumentParser.register("/by", true);
    }

    @Override
    public String getName() {
        return "deadline";
    }

    @Override
    public void execute(Model model, UiOutput uiOutput, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = argumentParser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] byArgs = switchArgs.get("/by");
        if (byArgs.length == 0) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }

        Deadline d = new Deadline(ArgumentParser.concatenate(comArgs), ArgumentParser.concatenate(byArgs));
        TaskList tasks = model.copyOfCurrentTasks();
        tasks.add(d);
        model.update(this, tasks);
        uiOutput.say("added: " + d);
    }
}
