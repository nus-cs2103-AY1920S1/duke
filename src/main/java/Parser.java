import java.text.ParseException;

public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] token = command.split(" ");
        String[] temp;
        String taskDesc;
        Task t;
        switch (token[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            if (token.length<=1) {
                throw new DukeException("Give me a goddamn numbered task to do.");
            }
            return new DoneCommand(Integer.parseInt(token[1]));
        case "delete" :
            if (token.length<=1) {
               throw new DukeException("Give me a goddamn numbered task to delete.");
            }
            return new DeleteCommand(Integer.parseInt(token[1]));
        case "todo":
            if (token.length<=1) {
                throw new DukeInvalidTaskDescriptionException("ToDo");
            }
            return new ToDoCommand(token[1]);
        case "deadline":
            temp = command.split("/by");
            taskDesc = temp[0].substring(8).trim();
            if(taskDesc.equals("")) {
                throw new DukeInvalidTaskDescriptionException("Deadline");
            } else if(temp.length < 2) {
                throw new DukeInvalidTaskTimeException("deadline");
            }
            return new DeadlineCommand(taskDesc, temp[1].trim());
        case "event":
            temp = command.split("/at");
            taskDesc = temp[0].substring(5).trim();
            if(taskDesc.equals("")) {
                throw new DukeInvalidTaskDescriptionException("Event");
            } else if (temp.length < 2) {
                throw new DukeInvalidTaskTimeException("event");
            }
            return new EventCommand(taskDesc, temp[1].trim());
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}


