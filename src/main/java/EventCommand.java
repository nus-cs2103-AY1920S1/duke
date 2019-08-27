public class EventCommand extends Command{
    private String description;
    private String duringWhen;

    public EventCommand(String details) throws DukeMissingDescriptionException, DukeEmptyDescriptionException {
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("event");
        } else {
            String[] info = details.split("/at");
            if (info.length == 1) {
                throw new DukeMissingDescriptionException("event");
            } else {
                this.description = info[0].trim();
                if(info[1].split("/").length == 3) {
                    String[] date = info[1].trim().split(" ");
                    String dateWord = Command.dateToWords(date[0]);
                    String time = Command.timeConverter(date[1]);
                    this.duringWhen = dateWord + ", " + time;
                } else {
                    this.duringWhen = info[1].trim();
                }
            }
        }
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        Event current = new Event(this.description, this.duringWhen);
        tasks.add(current);
        storage.addEventData(this.description, this.duringWhen);
        ui.printAddEventMessage(current, tasks.size());
    }
}
