package duke.commands;

import duke.Command;
import duke.Duke;
import duke.DukeException;

import java.util.Map;

public class Deadline extends Command {
    public Deadline(Duke duke) {
        super(duke);
        name = "deadline";

        parser.register("/by", true);
    }

    @Override
    public void execute(String[] args) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(name);
        if(comArgs.length == 0) throw new DukeException("The description of a deadline cannot be empty.");

        String[] byArgs = switchArgs.get("/by");
        if(byArgs.length == 0) throw new DukeException("The date of a deadline cannot be empty.");

        duke.addTask(new duke.tasks.Deadline(Parser.concatenate(comArgs), Parser.concatenate(byArgs)));
    }
}
