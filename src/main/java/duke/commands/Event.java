package duke.commands;

import duke.Command;
import duke.Duke;
import duke.DukeException;

import java.util.Map;

public class Event extends Command {
    protected Parser parser = new Parser();
    public Event(Duke duke) {
        super(duke);
        name = "event";

        parser.register("/at", true);
    }
    
    @Override
    public void execute(String[] args) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(name);
        String[] atArgs = switchArgs.get("/at");
        duke.addTask(new duke.tasks.Event(Parser.concatenate(comArgs), Parser.concatenate(atArgs)));
    }
}
