public class Parser {
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done")) {
            //TODO Implement DukeException TaskNotFoundException
            int taskNumber = Character.getNumericValue(command.charAt(5)) - 1;
            return new DoneCommand(taskNumber);
        } else if (command.startsWith("delete")) {
            int taskNumber = Character.getNumericValue(command.charAt(7)) - 1;
            return new DeleteCommand(taskNumber);
        } else {
            Task userTask;

            if (command.startsWith("todo")) {
                String task = command.replaceFirst("todo", "");
                if (task.isEmpty()) {
                    throw new EmptyToDoDescriptionException("The description of a todo cannot be empty. ");
                } else {
                    //removes space in the beginning
                    task = task.replaceFirst(" ", "");
                    userTask = new ToDo(task);
                }
            } else if (command.startsWith("deadline")) {
                String task = command.replaceFirst("deadline ", "");
                String[] taskInformation = task.split(" /by ");
                userTask = new Deadline(taskInformation[0], taskInformation[1]);
            } else if (command.startsWith("event")) {
                String task = command.replaceFirst("event ", "");
                String[] taskInformation = task.split(" /at ");
                userTask = new Event(taskInformation[0], taskInformation[1]);
            } else {
                throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new AddCommand(userTask);
        }
    }
}