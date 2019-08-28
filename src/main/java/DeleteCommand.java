public class DeleteCommand extends Command {
    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    private String generateResponse(Task deleted, TodoList tasks) {
        String[] lines = {"Noted. I've removed this task: ",
                          "\t" + deleted.toString(),
                          String.format("Now you have %d tasks in the list", tasks.length())};
        return String.join("\n", lines);
    }

    @Override
    public String run(TodoList tasks, Storage storage) {
        if (index > tasks.length()) {
            throw new DukeException("OOPS!!! That's an invalid index");
        }
        Task deleted = tasks.delete(index);
        super.run(tasks, storage);
        return this.generateResponse(deleted, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
