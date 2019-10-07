package taskchick.command;

import taskchick.exception.TaskChickException;
import taskchick.storage.Storage;
import taskchick.tasklist.TaskList;

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
     * Parses the command given to Task Chick and creates a ScheduleCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return ScheduleCommand object to be created.
     * @throws TaskChickException If date given is not in number format (eg. 9/Sep/2019), or not in dd/mm/yyyy format.
     */
    public static ScheduleCommand process(String[] fullCommand) throws TaskChickException {
        try {
            String[] dateArray = fullCommand[1].split("/", 3);
            int dayFromArray = Integer.parseInt(dateArray[0]);
            int monthFromArray = Integer.parseInt(dateArray[1]);
            int yearFromArray = Integer.parseInt(dateArray[2]);
            return new ScheduleCommand(new int[]{dayFromArray, monthFromArray, yearFromArray});
        } catch (NumberFormatException e) {
            throw new TaskChickException("OOPS!!! Please enter the date in the format DD/MM/YYYY");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskChickException("OOPS!!! Please specify the date you want to check in the format "
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
