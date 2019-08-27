import java.security.InvalidParameterException;
import java.util.*;

/*public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        TasksHolder tasksHolder = new TasksHolder();
        FileManager fileManager = new FileManager();
        fileManager.populateTasksHolder(tasksHolder);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                fileManager.populateDukeTxt(tasksHolder);
                break;
            } else if (line.equals("list")) {
                tasksHolder.printTasks();
            } else if (line.contains(" ") && line.split(" ")[0].equals("done")) {
                int index = Integer.valueOf(line.split(" ")[1]);
                tasksHolder.taskDone(index);
            } else if (line.contains(" ") && line.split(" ")[0].equals("delete")) {
                int index = Integer.valueOf(line.split(" ")[1]);
                tasksHolder.deleteTask(index);
            } else if (line.contains("/")) {
                String taskCat = line.split(" ")[0];
                if (taskCat.equals("deadline")) {
                    String endDate = "";
                    if(line.split("/").length == 3) {
                        endDate = line.split("/")[1].split(" ")[1];
                    } else {
                        endDate = line.split("/by ")[1];
                    }
                    String taskName = line.split("/")[0].split("deadline ")[1];
                    Task currTask = new Deadline(taskName, endDate);
                    tasksHolder.addTask(currTask);
                } else if (taskCat.equals("event")) {
                    String eventDate = line.split("/")[1].split("at ")[1];
                    String taskName = line.split("/")[0].split("event ")[1];
                    Task currTask = new Event(taskName, eventDate);
                    tasksHolder.addTask(currTask);
                }
            } else if (line.contains(" ") && line.split(" ")[0].equals("todo")) {
                String taskName = line.split("todo ")[1];
                Task currTask = new Todo(taskName);
                tasksHolder.addTask(currTask);
            } else {
                if(line.equals("todo")){
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }
}*/

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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}