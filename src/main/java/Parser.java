import commands.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class Parser {
    static Command parse(String command) throws DukeException, ParseException {
        String[] task = command.split(" ", 2);
        int taskSize = task.length;

        //action required
        command = task[0];

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        switch (command) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(task[1].trim()));
        case "bye":
            return new EndCommand();
        case "delete":
            if (taskSize < 2) {
                throw new DukeException(command);
            } else {
                return new DeleteCommand(Integer.parseInt(task[1].trim()));
            }
        case "todo":
            if (taskSize < 2) {
                throw new DukeException(command);
            } else {
                return new AddCommand(new Todo(task[1].trim()));
            }
        case "deadline":
            if (taskSize < 2) {
                throw new DukeException(command);
            } else {
                String[] d = task[1].split(" /by ", 2);
                return new AddCommand(new Deadline(d[0].trim(), formatter.parse(d[1])));
            }
        case "event":
            if (taskSize < 2) {
                throw new DukeException(command);
            } else {
                String[] e = task[1].split(" /at ", 2);
                return new AddCommand(new Event(e[0].trim(), formatter.parse(e[1])));
            }
        default:
            throw new DukeException();
        }
    }
}
