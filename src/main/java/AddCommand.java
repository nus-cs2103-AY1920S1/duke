/**
 * The AddCommand class takes care of any commands that would
 add tasks onto the current task list.
 */
public class AddCommand extends Command {
    /**
     * The type of task, whether it is a ToDo, Event or Deadline task.
     */
    private String typeOfTask;

    /**
     * Constructs and initializes the attributes of a new AddCommand object.
     * @param typeOfTask The type of AddCommand task to be added to the task list.
     * @param commandText The portion of text that contains the details of the task.
     */
    public AddCommand(String typeOfTask, String commandText) {
        super();
        this.description = commandText;
        this.typeOfTask = typeOfTask;
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     * @throws DukeException Possibility of throwing a DukeException due to
     *      an exception occuring in the running of the application.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
            switch (typeOfTask) {
                case "todo":
                    if (description.equals("")) {
                        throw new InvalidDescriptionException("Wrong description");
                    }
                    ui.showText(task.addTask(new ToDos(description)));
                    break;

                case "deadline":
                    String[] input2 = description.trim().split("/by");
                    if (input2.length != 2) {
                        throw new InvalidDescriptionException("Wrong description");
                    }
                    ui.showText(task.addTask(new Deadlines(input2[0], input2[1])));
                    break;

                case "event":
                    String[] input3 = description.trim().split("/at");
                    if (input3.length != 2) {
                        throw new InvalidDescriptionException("Wrong description");
                    }
                    ui.showText(task.addTask(new Event(input3[0], input3[1])));
                    break;
            }
    }
}
