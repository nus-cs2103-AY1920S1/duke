abstract class AddCommand extends Command {
    public static void addTask(TaskList tasks, Ui ui, Storage storage, Task newTask) {
        tasks.add(newTask);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(6, newTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.writeList(tasks);
    }

}
