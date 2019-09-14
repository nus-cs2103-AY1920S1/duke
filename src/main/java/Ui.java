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
        return "Greetings, Comrade.";
    }
    public String markDone(Task doneTask) {
        /**
         *  ui function to show user message
         *  when task has been marked as done
         * @params Task doneTask that is completed
         *  @return String
         */
        return "Glorious day, Comrade, you have finished another chore!:\n"+doneTask.printTask();
    }
    public String addTask(Task task) {
        /**
         *  ui function to show message when task has been added
         *  wrapped in solid lines
         *  also prints current number of tasks left
         * @params Task task to be added
         *  @return none
         */
        return("Da. Your task has been recorded:\n"+" " + task.printTask() + "\n" +
                "You have " + tasks.getList().size() + " chores left, Comrade.\n");
    }
    public String delTask(Task task) {
        /**
         *  ui function to show message when task has been deleted
         *  wrapped in solid lines
         * @params Task task to be deleted
         *  @return none
         */
        return "Yes Comrade, I've removed your task: \n"+" " + task.printTask() +
                "You have " + tasks.getList().size() + " tasks left, Comrade.\n";
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
        return "Goodbye Comrade, go forth and bring glory to the Soviet Union!\n";
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
        String mainString = "Here are your chores, Comrade:\n";

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
        String matchingString = "Here are the tasks that match your description, Comrade:\n";
        ArrayList<Task> matchingTasks = tasks.findTasks(keyWord);
        int taskCount = 1;
        for (Task match: matchingTasks) {
            matchingString = matchingString + taskCount + "." + match.printTask() + "\n";
            taskCount++;
        }
        return matchingString;
    }
}
