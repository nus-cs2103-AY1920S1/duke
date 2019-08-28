public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }


    private String generateResponse(TodoList tasks) {
        String[] lines = {"Got it. I've added this task: ", "\t" + this.task.toString(), String.format("Now you have %d tasks in the list.", tasks.length())};
        return String.join("\n", lines);
    }
    @Override
    public String run(TodoList tasks, Storage storage) {
        tasks.add(this.task);
        super.run(tasks, storage);
        return this.generateResponse(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
