public class DeadlineCommand extends AddCommand {
    DeadlineCommand(String details) {
        super(details);
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        String[] taskDetails = details.split(" /by ");
        tasks.add(new Deadline(taskDetails[0], taskDetails[1]));
        super.execute(tasks, ui, storage);
    }
}
