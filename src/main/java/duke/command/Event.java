package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Map;

public class Event extends Command {

    public Event(String[] args) {
        super(args);

        parser.register("/at", true);
    }

    @Override
    public String getName() {
        return "event";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String[] atArgs = switchArgs.get("/at");
        if (atArgs.length == 0) {
            throw new DukeException("The date of an event cannot be empty.");
        }

        duke.task.Event e = new duke.task.Event(Parser.concatenate(comArgs), Parser.concatenate(atArgs));
        tasks.add(e);
        ui.say("added: " + e);
    }
}
