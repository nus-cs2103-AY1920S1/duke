package duke.commands;

import duke.Command;
import duke.Duke;
import duke.DukeException;

import java.util.Map;

public class Deadline extends Command {
    protected Parser parser = new Parser();
    public Deadline(Duke duke) {
        super(duke);
        name = "deadline";

        parser.register("/by", true);
    }

    @Override
    public void execute(String[] args) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(name);
        String[] byArgs = switchArgs.get("/by");
        duke.addTask(new duke.tasks.Deadline(Parser.concatenate(comArgs), Parser.concatenate(byArgs)));
    }
}
