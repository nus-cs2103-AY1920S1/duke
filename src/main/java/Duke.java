import java.util.Scanner;

/**
 * Duke Chat Class.
 *
 * A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }

    /** Handles user chat interaction */
    public void run() {
        // Create default hi/bye strings
        String logo = " ____        _        \n\t"
                    + "|  _ \\ _   _| | _____ \n\t"
                    + "| | | | | | | |/ / _ \\\n\t"
                    + "| |_| | |_| |   <  __/\n\t"
                    + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "G'day mate! I'm Duke.\n\tWhatcha need help with?";
        String bye = "Bye mate. Catch you later!";

        Scanner sc = new Scanner(System.in);
        String command = "";

        ui.reply(greeting); //say greetings

        // Begin chat interaction
        do {
            try {
                command = sc.nextLine();
                if (command.equalsIgnoreCase("list")) {
                    ui.listTasks(tasks);
                } else if (command.toLowerCase().startsWith("done")) {
                    Task task = tasks.doneTask(command);
                    ui.replyDoneTask(task);
                } else if (command.toLowerCase().startsWith("todo")) {
                    Task task = tasks.addTodo(command);
                    ui.replyAddTask(task, tasks.size());
                } else if (command.toLowerCase().startsWith("deadline")) {
                    Task task = tasks.addDeadline(command);
                    ui.replyAddTask(task, tasks.size());
                } else if (command.toLowerCase().startsWith("event")) {
                    Task task = tasks.addEvent(command);
                    ui.replyAddTask(task, tasks.size());
                } else if (command.toLowerCase().startsWith("delete")) {
                    Task task = tasks.deleteTask(command);
                    ui.replyDeleteTask(task, tasks.size());
                } else if (command.equalsIgnoreCase("bye")) {
                    ui.reply(bye); //say goodbye
                } else {
                    throw new UnknownCommandException("OOPS!!! Sorry mate, I don't geddit.");
                }
                storage.save(tasks); // gracefully save tasks after every command, invalid or not.
            } catch (DukeException e) {
                ui.reply(e.getMessage());
            }
        } while (!command.equalsIgnoreCase("bye"));
    }

}
