import java.util.List;

class DeleteCommand implements Command {
    private final List<Task> tasks;

    DeleteCommand(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<String> run(String[] words) {
        Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
        return List.of("Noted. I've removed this task:", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
