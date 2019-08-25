import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommand extends Command {
    private String addType;
    private String userInput;

    public AddCommand(String addType, String userInput, String[] inputSplit, String filePath) {
        super(filePath, inputSplit);
        this.addType = addType;
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (addType) {
        case "todo":
            if (inputSplit.length == 1) {
                // Exception if no description after "todo"
                throw new DukeException(ui.separationLine
                        + "\n     \u2639 OOPS!!! The description of a todo cannot be empty.\n"
                        + ui.separationLine + "\n");
            }
            ToDo todo = new ToDo(userInput.replace("todo ", ""), 0);
            tasks.addToList(todo);
            String writeStringT = todo.type + " " + 0 + " " + todo.description + "\n";
            storage.writeToFile(filePath, writeStringT, true);
            ui.printAddNotification(todo.toString(), tasks.getSize());
            break;
        case "deadline":
            if (!userInput.contains(" /by ")) {
                // Exception for invalid deadline format
                throw new DukeException(ui.separationLine
                        + "\n     \u2639 OOPS!!! For deadline please use the format\n"
                        + "               \"deadline description /by end time\"\n"
                        + ui.separationLine + "\n");
            }
            String[] splitStringD = userInput.split(" /by ");
            Date inputDateD = Parser.convertToDate(splitStringD[1], Parser.dateFormats);
            String inputDateStrD = inputDateD == null ? splitStringD[1]
                    : new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(inputDateD);
            Deadline deadline = new Deadline(splitStringD[0].replace("deadline ", ""), 0,
                    inputDateStrD);
            tasks.addToList(deadline);
            String writeStringD = deadline.type + " 0" + " " + deadline.description + " | " + deadline.endTime
                    + "\n";
            storage.writeToFile(filePath, writeStringD, true);
            ui.printAddNotification(deadline.toString(), tasks.getSize());
            break;
        case "event":
            if (!userInput.contains(" /at ")) {
                // Exception for invalid deadline format
                throw new DukeException(ui.separationLine
                        + "\n     \u2639 OOPS!!! For event please use the format\n"
                        + "               \"event description /at period\"\n"
                        + ui.separationLine + "\n");
            }
            String[] splitStringE = userInput.split(" /at ");
            Event event = new Event(splitStringE[0].replace("event ", ""), 0,
                    splitStringE[1]);
            tasks.addToList(event);
            String writeStringE = event.type + " 0" + " " + event.description + " | " + event.eventPeriod
                    + "\n";
            storage.writeToFile(filePath, writeStringE, true);
            ui.printAddNotification(event.toString(), tasks.getSize());
            break;
        default:
            break;
        }
    }
}
