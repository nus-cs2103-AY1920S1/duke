package duke.commands;

import duke.Command;
import duke.Duke;
import duke.DukeException;
import duke.Parser;

import java.util.Map;

public class Todo extends Command {
    public Todo(Duke duke) {
        super(duke);
        name = "todo";
    }
    public void execute(String[] args) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(name);
        if(comArgs.length == 0) throw new DukeException("The description of a todo cannot be empty.");

        duke.addTask(new duke.tasks.Todo(Parser.concatenate(comArgs)));
    }
}
