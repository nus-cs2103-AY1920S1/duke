import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filePath) throws IOException, IllegalCommandException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showloadingerror(e);
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();
        try {
            while(true) {
                String command = ui.entercommand();
                if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                        tasks.addtask(command);
                        ui.addedmessage(tasks.getTasklist());
                } else if (command.contains("delete")) {
                    String deletedtask = tasks.deletetask(command);
                    ui.deletedmessage(tasks.getTasklist(), deletedtask);
                } else if (command.contains("done")) {
                    String taskdonestr = tasks.donetask(command);
                    ui.donemessage(taskdonestr);
                } else if (command.contains("list")) {
                    ui.showlist(tasks.getTasklist());
                } else if (command.contains("bye")) {
                    storage.save(tasks.getTasklist());
                    ui.byemessage();
                    break;
                } else if (command.contains("find")) {
                    ArrayList<Task> foundTask = tasks.findTasks(command);
                    ui.showFoundMessage(foundTask);
                } else {
                    throw new IllegalCommandException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (IllegalCommandException errormsg) {
            ui.illegalcommanderror(errormsg);
        }


    }

    public static void main(String[] args) throws IOException, IllegalCommandException {
        new Duke("/Users/kchensheng/Documents/NUS/Y2" +
                "/Sem1/CS2103/chen_sheng_duke/data/data.txt").run();
    }

}
