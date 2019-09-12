public class AddEventCommand extends Command {

    String task;
    String time;

    public AddEventCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        if (tasklist.size() >= 100) {
            ui.sendMessage("You can add no more than 100 tasks!");
        } else {
            DateTime dateTime = new DateTime(time);
            tasklist.add(new Event(task, dateTime.toString()));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }
}
