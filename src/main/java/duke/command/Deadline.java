package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Map;

public class Deadline extends Command {
    public Deadline(String[] args) {
        super(args);

        parser.register("/by", true);
    }

    @Override
    public String getName() {
        return "deadline";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] byArgs = switchArgs.get("/by");
        if (byArgs.length == 0) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }

        duke.task.Deadline d = new duke.task.Deadline(Parser.concatenate(comArgs), Parser.concatenate(byArgs));
        tasks.add(d);
        ui.say("added: " + d);
    }
}
