import java.util.ArrayList;

public class Ui {
    private TaskList tasks;
    private Storage storage;

    public void link(TaskList dukeTaskList, Storage dukeStorage) {
        /**
         *  links ui to tasklist and storage
         * @params TaskList, Storage used for duke
         *  @return none
         */
        tasks = dukeTaskList;
        storage = dukeStorage;
    }
    public String showWelcome() {
        /**
         *  prints welcome message with lines to beautify
         * @params none
         *  @return none
         */
        return "Hello, I'm Duke\nWhat can I do for you?";
    }
    public String markDone(Task doneTask) {
        /**
         *  ui function to show user message
         *  when task has been marked as done
         * @params Task doneTask that is completed
         *  @return String
         */
        return "Nice! I've marked this task as done:\n"+doneTask.printTask();
    }
    public String addTask(Task task) {
        /**
         *  ui function to show message when task has been added
         *  wrapped in solid lines
         *  also prints current number of tasks left
         * @params Task task to be added
         *  @return none
         */
        return("Got it. I've added this task:\n"+" " + task.printTask() + "\n" +
                "Now you have " + tasks.getList().size() + " tasks in the list.\n");
    }
    public String delTask(Task task) {
        /**
         *  ui function to show message when task has been deleted
         *  wrapped in solid lines
         * @params Task task to be deleted
         *  @return none
         */
        return "Noted. I've removed this task: \n"+" " + task.printTask() +
                "Now you have " + tasks.getList().size() + " tasks in the list.\n";
    }
    public String goodBye() {
        /**
         * say goodbye:
         * saves current tasks into text file by saving over past text file
         * then prints goodbye message
         * @params none
         * @returns none
         */
        storage.saveToTextFile();
        return "Bye. Hope to see you again soon!\n";
    }
    public String list() {
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
        String mainString = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskArr.size(); i++) {
            // printInt to put number for printing
            int printInt = i + 1;
            Task currTask = taskArr.get(i);
            String numAsString = printInt + ".";
            mainString += numAsString + currTask.printTask() + "\n";
        }
        return mainString;

    }
    public String find(String keyWord) {
        /**
         *  uses task list to find tasks
         *  that have the keywords as a substring
         *  then prints results wrapped inside
         *  2 solid lines
         * @params keyword to search
         * @return none
         */
        String matchingString = "Here are the matching tasks in your list:\n";
        ArrayList<Task> matchingTasks = tasks.findTasks(keyWord);
        int taskCount = 1;
        for (Task match: matchingTasks) {
            matchingString = matchingString + taskCount + "." + match.printTask() + "\n";
            taskCount++;
        }
        return matchingString;
    }
}
