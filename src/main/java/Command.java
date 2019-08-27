import customexceptions.DukeException;
import customexceptions.IncorrectInputException;
import tasks.TaskList;

/**
 * Represents a command that is perceived by Duke.
 */
public class Command {
    private String input;

    /**
     * Constructor for command object.
     * @param input The input given by the user.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command that Duke receieves.
     * @param ui Ui object.
     * @param t TaskList object storing list of tasks.
     * @param s Storage object storing data input by the user.
     * @throws DukeException if there is an error with the user input.
     */
    public void execute(Ui ui, TaskList t, Storage s) throws DukeException {
        if (input.length() <= 3) {
            throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            if (input.equals("list")) {
                ui.printList(t,s);
            } else if (input.substring(0, 4).equals("done")) {
                Parser.parseDone(input,t,s);
            } else {
                if (input.substring(0, 4).equals("todo")) {
                    Parser.parseToDo(input,t,ui,s);
                } else if (input.length() <= 5) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.substring(0, 5).equals("event")) {
                    Parser.parseEvent(input,t,ui,s);
                } else if (input.substring(0, 6).equals("delete")) {
                    Parser.parseDelete(input,t,ui,s);
                } else if (input.length() <= 7) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.substring(0, 8).equals("deadline")) {
                    Parser.parseDeadline(input,t,ui,s);
                } else {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                int numberOfTasks = t.getCommandList().size();
                if (numberOfTasks == 1) {
                    System.out.println("Now you have " + numberOfTasks + " task in the list");
                } else
                    System.out.println("Now you have " + numberOfTasks + " tasks in the list");
            }
        }
    }
}
