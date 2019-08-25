public class AddEventCommand extends Command {
    protected String details;

    public AddEventCommand(String details) {
        super();
        this.details = details;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] detailsSplit = details.split( "/at");
        if (detailsSplit.length == 0 || detailsSplit[0].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        if (detailsSplit.length < 2 || detailsSplit[1].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event requires a task and/or a scheduled time");
        }
        String event = detailsSplit[0].trim();
        String timings = detailsSplit[1].trim();
        try {
            String[] startAndEndSplit = timings.split("/to");
            if (startAndEndSplit[0].trim().length() == 0) {
                throw new DukeException("\u2639 OOPS!!! Please input a start time.");
            }
            String startDetails = startAndEndSplit[0].trim();
            String[] startDateAndTimeSplit = startDetails.split(" ");
            String startDate = startDateAndTimeSplit[0];
            String startTime = "";
            if (startDateAndTimeSplit.length == 2) {
                startTime = startDateAndTimeSplit[1];
            } else if (startDateAndTimeSplit.length > 2) {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String endDetails = "";
            String endDate = "";
            String endTime = "";
            if (startAndEndSplit.length == 2) {
                endDetails = startAndEndSplit[1].trim();
                String[] endDateAndTimeSplit = endDetails.split(" ");
                endDate = endDateAndTimeSplit[0];
                if (endDateAndTimeSplit.length == 2) {
                    endTime = endDateAndTimeSplit[1];
                } else if (endDateAndTimeSplit.length > 2){
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            Date eventStartDate = new Date(startDate);
            Time eventStartTime = new Time(startTime);
            Date eventEndDate = new Date(endDate);
            Time eventEndTime = new Time(endTime);
            Task taskEvent = new Event(event, eventStartDate, eventStartTime, eventEndDate, eventEndTime);
            tasks.addTask(taskEvent);
            int numberOfTasks = tasks.getListSize();
            ui.printAddedMessage(taskEvent, numberOfTasks);
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    public boolean isExit() {
        return false;
    }
}
