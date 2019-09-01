public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }

    }

    private void run() {
        ui.showWelcome();
        try {
            while(true) {
                String command = ui.enterCommand();
                if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                        tasks.addTask(command);
                        ui.getAddedMessage(tasks.getTaskList());
                } else if (command.contains("delete")) {
                    String deletedTask = tasks.deleteTask(command); // retrieve the deleted task.
                    ui.getDeletedMessage(tasks.getTaskList(), deletedTask);
                } else if (command.contains("done")) {
                    String taskDoneStr = tasks.doneTask(command);  // retrieve the task that is done.
                    ui.getDoneMessage(taskDoneStr);
                } else if (command.contains("list")) {
                    ui.showList(tasks.getTaskList());
                } else if (command.contains("bye")) {
                    storage.save(tasks.getTaskList());
                    ui.getByeMessage();
                    break;
                }
                else {
                    throw new IllegalCommandException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (IllegalCommandException errorMsg) {
            ui.getIllegalCommandError(errorMsg);
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/kchensheng/Documents/NUS/Y2" +
                "/Sem1/CS2103/chen_sheng_duke/data/data.txt").run();
    }
}
