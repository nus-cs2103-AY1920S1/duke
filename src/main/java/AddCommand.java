class AddCommand extends Command {

    private Task task;

    AddCommand(String description) {
        super(false);
        task = new Todo(description);
    }

    AddCommand(String action, String description, String time) throws DukeException {
        super(false);
        if (action.equals("event")) {
            task = new Event(description, time);
        } else if (action.equals("deadline")) {
            task = new Deadline(description, time);
        } else {
            throw new DukeException("Unrecognised command :(");
        }
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task, ui);
    }
}
