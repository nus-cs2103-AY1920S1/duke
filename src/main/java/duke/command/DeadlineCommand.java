package duke.command;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;
import duke.ui.DukeUI;
import duke.tasklist.TaskList;
import duke.storagedata.StorageData;
import duke.task.Deadline;
public class DeadlineCommand extends Command{
    private String description;
    private String byWhen;
    public DeadlineCommand(String details) throws DukeEmptyDescriptionException, DukeMissingDescriptionException {
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("deadline");
        } else {
            String[] info = details.split("/by");
            if (info.length == 1) {
                throw new DukeMissingDescriptionException("deadline");
            } else {
                this.description = info[0].trim();
                if(info[1].split("/").length == 3) {
                      String[] date = info[1].trim().split(" ");
                      String dateWord = Command.dateToWords(date[0]);
                      String time = Command.timeConverter(date[1]);
                      this.byWhen = dateWord + ", " + time;
                } else {
                    this.byWhen = info[1].trim();
                }
            }
        }
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        String details = this.getDetails();
        Deadline current = new Deadline(this.description, this.byWhen);
        tasks.add(current);
        storage.addDeadlineData(this.description, this.byWhen);
        ui.printAddDeadlineMessage(current, tasks.size());
    }
}
