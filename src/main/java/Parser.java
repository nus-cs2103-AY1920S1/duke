import java.util.InputMismatchException;

public class Parser {

    /**
     * parse method will parse command string and return the correct Command.
     * @param command is the user input
     * @return will return the correct command
     */
    public static Command parse(String command) {
        String[] cmdList = command.split(" ");
        String keyword = cmdList[0];

        if (keyword.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (keyword.equalsIgnoreCase("list")) {
            return new ListCommand();

        } else if (keyword.equalsIgnoreCase("done")) {
            int idxToMarkAsDone = Integer.parseInt(cmdList[1]) - 1;
            return new DoneCommand(idxToMarkAsDone);

        } else if (keyword.equalsIgnoreCase("delete")) {
            int idxToBeRemoved = Integer.parseInt(cmdList[1]) - 1;
            return new DeleteCommand(idxToBeRemoved);

        } else { //it will be an AddCommand
            Task taskToBeAdded = handleNewTask(keyword, command);
            return new AddCommand(taskToBeAdded);
        }
    }

    private static Task handleNewTask(String keyword, String cmd) throws InputMismatchException {
        String date = "";
        String time = "";
        String desc = "";
        if (keyword.equalsIgnoreCase("deadline")) {
            String descriptionAndTime = cmd.substring(8);
            String[] details = descriptionAndTime.trim().split(" /by");
            if (descriptionAndTime.isEmpty() || details.length <= 1) {
                throw new InputMismatchException("The description of a deadline cannot be empty.");
            }
            String[] dateTime = details[1].trim().split(" ",2);
            if (dateTime.length < 2) {
                throw new InputMismatchException("Please input deadline time format.");
            }
            desc = details[0];
            date = dateTime[0];
            time = dateTime[1];
            return new Deadline(desc, date, time);

        } else if (keyword.equalsIgnoreCase("event")) {
            String descriptionAndTime = cmd.substring(5);
            String[] details = descriptionAndTime.trim().split(" /at");
            if (descriptionAndTime.isEmpty() || details.length <= 1) {
                throw new InputMismatchException("The description of an event cannot be empty.");
            }
            String[] dateTime = details[1].split(" ",2);
            if (dateTime.length < 2) {
                throw new InputMismatchException("Please input event time format.");
            }
            desc = details[0];
            date = dateTime[0];
            time = dateTime[1];
            return new Event(desc, date, time);

        } else if (keyword.equalsIgnoreCase("todo")) {
            desc = cmd.substring(4).trim(); //words after todo
            if (desc.isEmpty()) {
                throw new InputMismatchException("The description of a todo cannot be empty.");
            }
            return new Todo(desc);
        } else {
            throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
