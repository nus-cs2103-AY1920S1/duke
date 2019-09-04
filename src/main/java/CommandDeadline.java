/**
 * Encapsulates a command from user input String "deadline".
 */
public class CommandDeadline extends Command {

    public CommandDeadline(String command) {
        super(command);
        super.type = "Deadline: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        String[] commands = command.split("/by");
        if (this.command.isBlank() || command.indexOf("/") == 0) {
            throw new MissingDescriptionException(
                    "☹ Oh! Did you forget to add the task?");
        } else if (commands.length == 1) {
            throw new MissingDescriptionException(
                    "☹ OOPS!! Did you forget to add the deadline?");
        } else {
            String description = commands[0].trim();
            String deadline = commands[1].trim();
            Task dlTask = new Deadline(description, DateUtil.toTime(deadline));
            sh.add(dlTask);
            stor.save(sh.getList());
        }
    }

    @Override
    public String toString() {
        return "Deadline: " + command;
    }
}
