import java.io.IOException;

/**
 * Execute Done command in Duke.
 */
public class DoneCommand extends Command {


    private String command;

    /**
     *  Done Command constructor using string user input.
     * @param command user String input
     */
    public DoneCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Execute the Done command. Mark a task as done.
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return a string showing a task is marked as done
     * @throws DukeException if any of raw values are invalid
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException,AssertionError {
        String output = "";
        String[] donearr = command.split(" ");
        try {
            assert donearr.length > 1 : " Please enter the task number to be marked done.";
            assert donearr.length < 3 : " Please only enter a single digit to mark the task done.";
            if (donearr.length == 1 || donearr.length > 2) {
                throw new DukeException(" Please enter a single task number to be marked done.}");
            }
            Integer.parseInt(donearr[1]);
            int no = Integer.parseInt(donearr[1]) - 1;
            if (no >= task.getTaskList().size() || no <= -1) {
                throw new DukeException("      You help input an invalid task number. Task number cannot be more "
                        + "task size or less than 1");
            }
            Task donetask = task.getTaskList().get(no);
            output += ("     Nice! I've marked this task as done: \n");
            donetask.markAsDone();
            output += ("       " + donetask.toString() + "\n");
            storage.writeDone(no, donetask);
            return output + "\n" + ui.showLine();
        }  catch (IOException e) {
            return "WRITING ERROR";
        }   catch (NumberFormatException e) {
            return "Format is Done_<Integer>. Cannot be alphabets.";
        }
    }

}