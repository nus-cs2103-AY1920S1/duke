/**
 * Execute the Deadline command in Duke.
 */

import java.io.IOException;

public class DeadlineCommand extends Command {
    String command;

    /**
     * Deadline constructor with string command
     * @param command user string input
     */
    public DeadlineCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Execute the Deadline command
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @throws DukeException if any of raw values are invalid
     */
    @Override
    public void execute(TaskList task,Ui ui,Storage storage) throws DukeException {
        try {
            String[] deadlinetask = command.split("deadline ");
            String deadlineString = deadlinetask[1];
            String[] deadlinearr = deadlineString.split(" /by ");
            System.out.println("     Got it. I've added this task:");
            String[] date = deadlinearr[1].split("/");
            String[] tm = date[2].split(" ");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(tm[0]);
            String time = tm[1];
            time t = new time(day, month, year, time);
            Deadline deadline = new Deadline(deadlinearr[0], t.toString());
            task.addTask(deadline);
            int numberOfTask = task.getCount();
            System.out.println("       " + deadline.toString());
            System.out.println("     Now you have " + numberOfTask + " tasks in the list.");
            int checkdone = deadline.isDone ? 1 : 0;
            try {
                storage.appendToFile(checkdone + "/deadline/" + deadline.description + "/" + deadline.by + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}