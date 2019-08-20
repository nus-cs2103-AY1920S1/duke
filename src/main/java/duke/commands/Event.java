package duke.commands;

import duke.Command;
import duke.Duke;
import duke.DukeException;
import duke.Parser;

import java.util.Map;

public class Event extends Command {
    public Event(Duke duke) {
        super(duke);
        name = "event";

        parser.register("/at", true);
    }

    @Override
    public void execute(String[] args) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(name);
        if(comArgs.length == 0) throw new DukeException("The description of an event cannot be empty.");

        String[] atArgs = switchArgs.get("/at");
        if(atArgs.length == 0) throw new DukeException("The date of an event cannot be empty.");

        duke.addTask(new duke.tasks.Event(Parser.concatenate(comArgs), Parser.concatenate(atArgs)));
    }
}
