import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Ui ui = new Ui();

    public Parser() {
    }

    /**
     * parses and excecutes the command user enters
     * 
     * @param command   entered command
     * @param list      task list
     * @param formatter datetime formatter
     * @return false if user wishes to terminate program, true otherwise
     * @throws DukeException due to checking validity of command
     */
    public String parse(String command, TaskList list, DateTimeFormatter formatter, String message)
            throws DukeException {
        String verificationResult = Verify.checkCommandValidity(command, list, formatter);
        if (!verificationResult.equals("ok")) {
            message = verificationResult;
            return message;
        }
        if (command.equals("bye")) {
            message = ui.exit();
            return message;
        } else if (command.startsWith("list")) {
            message = ui.list(list);
        } else if (command.startsWith("done")) {
            list.get(Integer.parseInt(command.split(" ")[1]) - 1).setAsDone();
            message = ui.done(list, Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("find")) {
            TaskList matchingTasks = list.search(command.split(" ")[1]);
            message = ui.found(matchingTasks);
        } else if (command.startsWith("delete")) {
            Task t = list.remove(Integer.parseInt(command.split(" ")[1]));
            message = ui.delete(t);
        } else if (command.startsWith("todo")) {
            list.add(new Todo(command.substring(5), 0));
            message = ui.taskAdded(list);
        } else if (command.startsWith("event")) {
            String[] cmdSplit = command.substring(6).split(" /at ");
            list.add(new Event(cmdSplit[0], 0, LocalDateTime.parse(cmdSplit[1].split(" to ")[0], formatter),
                    LocalDateTime.parse(cmdSplit[1].split(" to ")[1], formatter)));
            message = ui.taskAdded(list);
        } else if (command.startsWith("deadline")) {
            list.add(new Deadline(command.substring(9).split(" /by ")[0], 0,
                    LocalDateTime.parse(command.substring(9).split(" /by ")[1], formatter)));
            message = ui.taskAdded(list);
        }
        return message;
    }
}