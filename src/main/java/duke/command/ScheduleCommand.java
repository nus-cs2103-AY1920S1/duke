package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to show users the tasks they have on a specific date.
 */
public class ScheduleCommand extends Command {

    private int[] date;

    /**
     * Creates a UpdateCommand object with the task number and new description to be updated assigned.
     *
     * @param date Specific date to be checked, in the array {dd, mm, yyyy}
     */
    public ScheduleCommand(int[] date) {
        this.date = date;
    }

    /**
     * Parses the command given to Duke and creates a ScheduleCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return ScheduleCommand object to be created.
     * @throws NumberFormatException If date given is not in number format (eg. 9/Sep/2019).
     */
    public static ScheduleCommand process(String[] fullCommand) throws DukeException {
        try {
            String[] dateArray = fullCommand[1].split("/", 3);
            int dayFromArray = Integer.parseInt(dateArray[0]);
            int monthFromArray = Integer.parseInt(dateArray[1]);
            int yearFromArray = Integer.parseInt(dateArray[2]);
            return new ScheduleCommand(new int[]{dayFromArray, monthFromArray, yearFromArray});
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter the date in the format DD/MM/YYYY");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Please specify the date you want to check in the format "
                    + "DD/MM/YYYY");
        }
    }

    /**
     * Executes the ScheduleCommand through the TaskList object.
     *
     * @param tasks Task list to refer to.
     * @param storage Unused.
     * @return Response to user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.displaySchedule(date);
    }
}
