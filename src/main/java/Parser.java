public class Parser {

    public Parser() {

    }

    public void executeCommand(String command) throws InvalidCommandException, MissingInputException,
            MissingDescriptionException {
        String[] commandWords = command.trim().split(" ");
        String commandType = commandWords[0];
        TaskList taskList = new TaskList();
        switch (commandType) {
            case "list":
                taskList.printList();
                break;
            case "done":
                int taskNumber = Integer.parseInt(commandWords[1]);
                taskList.markAsDone(taskNumber);
                break;
            case "delete":
                int taskNumber2 = Integer.parseInt(commandWords[1]);
                taskList.deleteTask(taskNumber2);
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(command, commandType);
                break;
            case "bye":
                Ui ui = new Ui();
                ui.exit();
                break;
            default:
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void addTask(String command, String taskType) throws MissingDescriptionException, MissingInputException {
        String desc = command.substring(taskType.length()).trim();
        Task task = new Task();
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("☹ OOPS!!! The description of " + taskType + " cannot be empty.");
        }
        switch (taskType) {
            case ("todo"):
                task = new Todo(desc);
                break;
            case ("deadline"):
                if (!desc.contains("/by")) {
                    throw new MissingInputException("☹ OOPS!!! The deadline cannot be found because /by is missing");
                }
                String[] splitDeadlineDesc = desc.split("/by");
                desc = splitDeadlineDesc[0].trim(); // first element in string array is solely the task description
                // second element in string array is the deadline of the task, unless it is not found
                String deadline;
                try {
                    deadline = splitDeadlineDesc[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    // above exception will be thrown when the splitDeadlineDesc only has one element
                    // this means that there is nothing after /by
                    throw new MissingInputException("☹ OOPS!!! The deadline cannot be found after /by");
                }
                task = new Deadline(desc, deadline);
                break;
            case ("event"):
                if (!desc.contains("/at")) {
                    throw new MissingInputException("☹ OOPS!!! The event date and time cannot be found because /at is missing");
                }
                String[] splitEventDesc = desc.split("/at"); // first element is simply the string description
                // second element would be the event time/day, unless it is not found
                desc = splitEventDesc[0].trim();
                String when;
                try {
                    when = splitEventDesc[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    // above exception will be thrown when the splitEventDesc only has one element
                    // this means that there is nothing after /at
                    throw new MissingInputException("☹ OOPS!!! The event date and time cannot be found after /at");
                }
                task = new Event(desc, when);
                break;
            default:
                break;
        }
        TaskList taskList = new TaskList();
        taskList.addTask(task);
    }

}
