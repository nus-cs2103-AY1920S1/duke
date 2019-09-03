/**
 * Handles addition of tasks upon user input eg. "todo complete CS2103"
 */
public class AddCommand extends Command {
    /**
     * Constructor that takes in keywords and indentation for execution of addition function
     * @param command First keyword entered by user determining task type
     * @param commandDetails Following integer indicating task name and date (if relevant)
     * @param INDENT Constant indentation from start of line (formatting)
     */
    public AddCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    /**
     * Handles addition of task from list
     * @param tasks Contains task list and operations to add from list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     * @throws DukeException Custom exception
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                String befTaskAddMessage = "Got it. I've added this task: \n" + INDENT + "   ";
                String aftTaskAddMessage = "Now you have " + (tasks.getList().size() + 1) + " tasks in the list.";
                if (!(commandDetails.equals(" ") || commandDetails.equals(""))) {
                    if (command.equals("todo")) {
                        tasks.addTask(new Task(commandDetails, "todo", false));
                        ui.printResponse(befTaskAddMessage + tasks.getList().get(tasks.getList().size() - 1) + "\n " + INDENT + aftTaskAddMessage);
                        storage.updateTodoFile(tasks.getListString());
                    } else if (command.equals("deadline")) {
                        try {
                            tasks.addDateTask(commandDetails.split(" /by ")[0], commandDetails.split(" /by ")[1], "deadline");
                            ui.printResponse(befTaskAddMessage + tasks.getList().get(tasks.getList().size() - 1) + "\n " + INDENT
                                    + aftTaskAddMessage);
                            storage.updateTodoFile(tasks.getListString());
                        } catch (Exception ex) {
                            ui.printError("☹ OOPS!!! Deadlines require a specific datetime after /by, "
                                    + "in format 'dd/MM/yyyy HHmm'");
                            System.out.println(ex);
                        }
                    } else if (command.equals("event")) {
                        try {
                            tasks.addDateTask(commandDetails.split(" /at ")[0], commandDetails.split(" /at ")[1], "event");
                            ui.printResponse(befTaskAddMessage + tasks.getList().get(tasks.getList().size() - 1) + "\n " + INDENT
                                    + aftTaskAddMessage);
                            storage.updateTodoFile(tasks.getListString());
                        } catch (Exception ex) {
                            ui.printError("☹ OOPS!!! Events require a specific datetime after /at, "
                                    + "in format 'dd/MM/yyyy HHmm'");
                        }
                    }
                } else {
                    ui.printError("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                }
            } else {
                ui.printError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n " + INDENT +
                        "Try todo, event, deadline, list, delete or done");
            }
        } catch(Exception err) {
            throw new DukeException(err.getMessage());
        }
    }
}
