import customexceptions.DukeException;
import customexceptions.IncorrectInputException;
import tasks.Parser;
import tasks.Storage;
import tasks.TaskList;
import tasks.Ui;

/**
 * Represents a command that is perceived by Duke.
 */
public class Command {
    private String input;

    /**
     * Constructor for command object.
     *
     * @param input The input given by the user.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command that Duke receieves.
     *
     * @param ui Ui object.
     * @param t  TaskList object storing list of tasks.
     * @param s  Storage object storing data input by the user.
     * @throws DukeException if there is an error with the user input.
     */
    public String execute(Ui ui, TaskList t, Storage s) throws DukeException {
        boolean find = false;
        String output = "";
        if (input.length() < 3) {
            throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (input.equals("bye")) {
            return ui.showBye();
        }
        else {
            assert(input.length() >= 3) : "input length incorrect";
            if (input.equals("list")) {
                output = ui.printList(t, s);
            } else if (input.substring(0, 4).equals("done")) {
                output = Parser.parseDone(input, t, s);
            } else {
                if (input.substring(0, 4).equals("todo")) {
                    output = Parser.parseToDo(input, t, ui, s);
                } else if (input.substring(0, 4).equals("find")) {
                    output = Parser.parseFind(input, t, ui, s);
                    find = true;
                } else if (input.length() <= 5) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.substring(0, 5).equals("event")) {
                    assert(input.length() > 5) : "input length incorrect";
                    output = Parser.parseEvent(input, t, ui, s);
                } else if (input.substring(0, 6).equals("delete")) {
                    output = Parser.parseDelete(input, t, ui, s);
                } else if (input.length() <= 7) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.substring(0, 8).equals("deadline")) {
                    output = Parser.parseDeadline(input, t, ui, s);
                } else {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                int numberOfTasks = t.getCommandList().size();
                StringBuilder sb = new StringBuilder();
                sb.append(output);
                if (find == false) {
                    sb.append("Now you have " + numberOfTasks + " tasks in the list");
                    find = false;
                }
            }
        }
        return output;
    }
}
