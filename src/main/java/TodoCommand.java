public class TodoCommand extends Command{
    public TodoCommand(String details) throws DukeEmptyDescriptionException{
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("todo");
        }
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        String details = this.getDetails();
        Todo current = new Todo(details);
        tasks.add(current);
        storage.addTodoData(details);
        ui.printAddTodoMessage(current, tasks.size());
    }
}
