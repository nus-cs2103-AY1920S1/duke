package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ScheduleCommand extends Command {

    private int[] date;

    public ScheduleCommand(int[] date) {
        this.date = date;
    }

    public static ScheduleCommand process(String[] fullCommand) throws NumberFormatException {
        if (fullCommand.length == 1) {
            throw new DukeException("OOPS!!! Please specify the date that you want to check.");
        }

        String[] dateArray = fullCommand[1].split("/", 3);

        if (dateArray.length < 3) {
            throw new DukeException("OOPS!!! Please enter the date in the format DD/MM/YYYY");
        }

        try {
            int dayFromArray = Integer.parseInt(dateArray[0]);
            int monthFromArray = Integer.parseInt(dateArray[1]);
            int yearFromArray = Integer.parseInt(dateArray[2]);
            return new ScheduleCommand(new int[]{dayFromArray, monthFromArray, yearFromArray});
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter the date in the format DD/MM/YYYY");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.findSchedule(date);
    }
}
