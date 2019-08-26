import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class OutputUtilities {

    private TaskList tasks;
    private List<Task> taskList;
    private LocalStorage storage;

    public OutputUtilities(TaskList tasks, LocalStorage storage) {
        this.tasks = tasks;
        this.taskList = tasks.getList();
        this.storage = storage;
    }


    public static void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

   public void sayHi() {
        System.out.println("\t Hello! I'm Duke.");

        try {
            storage.readFromTasksFileToList(tasks);
            System.out.println("\t This is where you left off previously: ");
            printLine();
        } catch (FileNotFoundException e) {
            System.out.println("\t Fetching failed. " + e.getMessage());
            System.out.println("\t Creating file now...");
            File dukeTxt = new File(Duke.filePath);
            try {
                dukeTxt.createNewFile();
                System.out.println("\t File created! " + dukeTxt.getAbsolutePath());
                System.out.println("\t Reading file...");
                printLine();
            } catch (IOException ioe) {
                System.out.println("\t File creation was not successful. " + ioe);
                System.out.println("Exiting system.");
                printLine();
                System.exit(-1);
            }

        }
        printTasks();
        printLine();
   }


   public static void printLine() {
        System.out.println("\t_______________________________");
    }


    public void printAddTaskMessage(Task t) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        List<Task> list = tasks.getList();
        System.out.println("\t Got it. I've added this task: \n" +
                "\t  " + t.toString() + "\n" +
                "\t Now you have " + list.size() + " " + pluralOrNot + " in the list");
    }


    public void printTasks() {
        if (taskList.size() == 0) System.out.println("\t No pending tasks");
        else {
            String pluralOrNot = taskList.size() == 1 ? "Task" : "Tasks";
            System.out.println("\t " + pluralOrNot);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print("\t " + (i + 1) + ".");
                System.out.println(taskList.get(i));
            }
        }
    }

    public void printMarkTaskAsCompletedMessage(Task t) {
        System.out.println("\t Nice! I've marked this task as done: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() );
    }

    public void printDeleteTaskMessage(Task t) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        System.out.println("\t I've removed this task: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() + "\n" +
                "\t Now you have " + taskList.size() + " " + pluralOrNot + " in the list");
    }
}
