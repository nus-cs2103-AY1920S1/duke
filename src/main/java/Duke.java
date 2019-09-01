import java.io.IOException;

/**
 * The main class for managing all the java files.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiate a Duke object when a directory parameter is passed
     * into it. Will also instantiate the Ui, Storage and TaskList objects.
     * @param filePath the directory for the designated path to store the tasks.
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showloadingerror(e);
            tasks = new TaskList();
        }

    }

    /**
     * Run the application Duke.
     */
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
                } else {
                    throw new IllegalCommandException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (IllegalCommandException errormsg) {
            ui.illegalcommanderror(errormsg);
        }


    }

    /**
     * Passed in the file path for the .txt file to instantiate the Duke object
     */
    public static void main(String[] args) {
        new Duke("/Users/kchensheng/Documents/NUS/Y2" +
                "/Sem1/CS2103/chen_sheng_duke/data/data.txt").run();
    }

}
