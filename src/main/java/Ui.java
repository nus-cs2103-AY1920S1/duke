import java.util.ArrayList;

public class Ui {
    private TaskList tasks;
    private Storage storage;
    public void Ui() {
    }
    public void link(TaskList dukeTaskList, Storage dukeStorage) {
        /**
         *  links ui to tasklist and storage
         * @params TaskList, Storage used for duke
         *  @return none
         */
        tasks = dukeTaskList;
        storage = dukeStorage;
    }
    public void showWelcome() {
        /**
         *  prints welcome message with lines to beautify
         * @params none
         *  @return none
         */
        printLine();
        System.out.println("Hello, I'm Duke\nWhat can I do for you?");
        printLine();
    }
    public void printLine() {
        /**
         *  helper function, prints formatted solid line
         * @params none
         *  @return none
         */
        System.out.println("____________________________________________________________");
    }
    public void markDone(Task doneTask) {
        /**
         *  ui function to show user message
         *  when task has been marked as done
         * @params Task doneTask that is completed
         *  @return none
         */
        printLine();
        System.out.print("Nice! I've marked this task as done:\n");
        System.out.println(doneTask.printTask());
        printLine();
    }
    public void addTask(Task task) {
        /**
         *  ui function to show message when task has been added
         *  wrapped in solid lines
         *  also prints current number of tasks left
         * @params Task task to be added
         *  @return none
         */
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.print(" " + task.printTask() + "\n");
        System.out.println("Now you have " + tasks.getList().size() + " tasks in the list.");
        printLine();
    }
    public void delTask(Task task) {
        /**
         *  ui function to show message when task has been deleted
         *  wrapped in solid lines
         * @params Task task to be deleted
         *  @return none
         */
        printLine();
        System.out.print("Noted. I've removed this task: \n");
        System.out.println(" " + task.printTask());
        System.out.println("Now you have " + tasks.getList().size() + " tasks in the list.");
        printLine();
    }
    public void goodBye() {
        /**
         * say goodbye:
         * saves current tasks into text file by saving over past text file
         * then prints goodbye message
         * @params none
         * @returns none
         */
        storage.saveToTextFile();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void list() {
        ArrayList<Task> taskArr = tasks.getList();
        /**
         *  prints all tasks from current taskList
         *  each task gets one line
         *  printed version of task outputs date time as
         *  "Day date Month SGT Year"
         *  output surrounded with solid lines
         * @params none
         *  @return none
         */
        String s = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskArr.size(); i++) {
            // printInt to put number for printing
            int printInt = i + 1;
            Task currTask = taskArr.get(i);
            s += currTask.printTask() + "\n";
        }
        printLine();
        System.out.println(s);
        printLine();
    }
    public void find(String keyWord) {
        /**
         *  uses task list to find tasks
         *  that have the keywords as a substring
         *  then prints results wrapped inside
         *  2 solid lines
         * @params keyword to search
         * @return none
         */
        printLine();
        ArrayList<Task> matchingTasks = tasks.findTasks(keyWord);
        for (Task match: matchingTasks) {
            System.out.println(match.printTask());
        }
        printLine();
    }
}
