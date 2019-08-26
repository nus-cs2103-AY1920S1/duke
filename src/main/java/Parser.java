import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Ui ui = new Ui();

    public Parser() {}
    /**
     * parses and excecutes the command user enters
     * @param command entered command
     * @param list task list
     * @param formatter datetime formatter
     * @return false if user wishes to terminate program, true otherwise
     * @throws DukeException due to checking validity of command
     */
    public boolean parse(String command, TaskList list, DateTimeFormatter formatter) throws DukeException {
        Verify.checkCommandValidity(command, list, formatter);
        if (command.equals("bye")) {
            ui.exit();
            return false;
        } else if (command.startsWith("list")) {
            ui.list(list);
        } else if (command.startsWith("done")) {
            list.get(Integer.parseInt(command.split(" ")[1])-1).setAsDone();
            ui.done(list, Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("delete")) {
            Task t = list.remove(Integer.parseInt(command.split(" ")[1]));
            ui.delete(t);
        } else if (command.startsWith("todo")) {
            list.add(new Todo(command.substring(5), 0));
            ui.taskAdded(list);
        } else if (command.startsWith("event")) {
            String[] cmdSplit = command.substring(6).split(" /at ");
            list.add(new Event(cmdSplit[0], 0, LocalDateTime.parse(cmdSplit[1].split(" to ")[0], formatter),
                    LocalDateTime.parse(cmdSplit[1].split(" to ")[1], formatter)));
            ui.taskAdded(list);
        } else if (command.startsWith("deadline")) {
            list.add(new Deadline(command.substring(9).split(" /by ")[0], 0, 
                    LocalDateTime.parse(command.substring(9).split(" /by ")[1], formatter)));
            ui.taskAdded(list);
        }
        return true;
    }
}