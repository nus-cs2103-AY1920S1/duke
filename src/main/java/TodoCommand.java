public class TodoCommand extends AddCommand {
    TodoCommand(String details) {
        super(details);
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        tasks.add(new Todo(details));
        super.execute(tasks, ui, storage);
    }
}
