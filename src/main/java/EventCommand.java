public class EventCommand extends AddCommand {
    EventCommand(String details) {
        super(details);
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        String[] taskDetails = details.split(" /at ");
        tasks.add(new Event(taskDetails[0], taskDetails[1]));
        super.execute(tasks, ui, storage);
    }
}
