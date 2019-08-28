public class DoneCommand extends Command {

    int index;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        index = Integer.parseInt(ui.remainingWords.trim());
        tasks.taskArrayList.get(index - 1).markAsDone();
        storage.writeData();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.taskArrayList.get(index - 1));
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
