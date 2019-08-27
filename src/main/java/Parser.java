public class Parser {

    private String[] validTaskTypes;
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of the Parser object. This mainly processes the input of the user.
     * @param taskList The task list of the program.
     */
    public Parser(TaskList taskList) {
        this.validTaskTypes = new String[] {"deadline", "event", "todo"};
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Processes the user input and performs an action based on it, if valid.
     * @param command The user's input.
     */
    public void parse(String command) {
        try {
            String[] commandArray = command.split(" ");
            if (command.equals("bye")) {
                ui.printBye();
            } else if (command.equals("list")) {
                ui.printList(taskList.getListItems());
            } else if (commandArray[0].equals("done")) {
                if (commandArray.length == 1) {
                    throw new DukeException("Please specify a task ID to set as done!");
                } else if (!commandArray[1].matches("\\d+")) {
                    throw new DukeException("The ID of the task must be an integer!");
                }
                int id = Integer.parseInt(commandArray[1]);
                taskList.markAsDone(id);
            } else if (commandArray[0].equals("delete")) {
                if (commandArray.length == 1) {
                    throw new DukeException("Please specify a task ID to delete!");
                } else if (!commandArray[1].matches("\\d+")) {
                    throw new DukeException("The ID of the task must be an integer!");
                }
                int id = Integer.parseInt(commandArray[1]);
                taskList.delete(id);
            } else if (isValidTaskType(commandArray[0])) {
                if (command.equals("todo")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                taskList.addTask(command);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.printException(e);
        }
    }

    /**
     * Check against the list of valid Task types to see if the command is valid.
     * @param taskType the first argument of the command input.
     * @return True if the first argument of the command input is valid.
     */
    public boolean isValidTaskType(String taskType) {
        for (String s : validTaskTypes) {
            if (taskType.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
