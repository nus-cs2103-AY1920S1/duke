import java.util.*;

public class Duke {

    public static final String DIVIDER = "    ____________________________________________________________";
    public static final String BLANKSPACE = "     ";

    private ArrayList<Task> _tasks;

    public Duke() {
        _tasks = new ArrayList<>();
    }

    private void dukeEcho(String... messages){
        System.out.println(DIVIDER);
        for (String msg : messages) {
            System.out.println(BLANKSPACE + msg);
        }
        System.out.println(DIVIDER);
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        dukeEcho("Hello! I'm Duke", "What can I do for you?");

        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {

            String[] arr = cmd.split(" ", 2);
            String firstWord = arr[0].toLowerCase();

            switch (firstWord) {
                case "list":
                    listTasks();
                    break;
                case "done":
                    finishTask(arr);
                    break;
                case "delete":
                    deleteTask(arr);
                    break;
                default:
                    addNewTask(arr);
            }
            cmd = sc.nextLine();
        }

        dukeEcho("Bye. Hope to see you again soon!");

    }


    private void addNewTask(String[] arr) {

        //Possible exception: command not recognized
        String taskType = arr[0].toLowerCase();
        boolean validTask = (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"));
        if (!validTask) {
            dukeEcho("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return;
        }

        String taskDetails;
        Task newTask;

        //Possible error: no parameters for task name and detail, or trailing whitespaces for detail
        try {
            taskDetails = arr[1];
        } catch (IndexOutOfBoundsException e) {
            dukeEcho("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
            return;
        } if (taskDetails.replaceAll("\\s+", "").equals("")) {
            dukeEcho("☹ OOPS!!! The description of a(n) " + arr[0] + " cannot be empty.");
            return;
        }

        switch(taskType) {
            case "todo":
                newTask = new ToDo(taskDetails);
                break;
            case "deadline":
                String[] deadlineTask = taskDetails.split("/");
                try {
                    newTask = new Deadline(deadlineTask[0], deadlineTask[1]);
                } catch (IndexOutOfBoundsException e) {
                    dukeEcho("OOPS!!! No deadline found");
                    return;
                }
                break;
            case "event":
                String[] eventTask = taskDetails.split("/");
                try {
                    newTask = new Event(eventTask[0], eventTask[1]);
                } catch (IndexOutOfBoundsException e) {
                    dukeEcho("OOPS!!! Event timing not found!");
                    return;
                }
                break;
            default:
                return;
        }

        _tasks.add(newTask);
        dukeEcho("Got it. I've added this task:",
                newTask.toString(),
                "Now you have " + _tasks.size() + " tasks in the list.");
    }

    private void finishTask(String[] arr){

        Task currTask = getTask(arr);
        if (currTask == null) return;

        // No error, proceed to mark task as complete
        currTask.finishTask();
        dukeEcho("Nice! I've marked this task as done:", currTask.toString());


    }

    private void deleteTask(String[] arr) {

        Task currTask = getTask(arr);
        if (currTask == null) return;

        // No error, proceed to delete task
        _tasks.remove(currTask);
        dukeEcho("Noted. I've removed this task:",
                currTask.toString(),
                "Now you have " + _tasks.size() + " tasks in the list.");
    }

    /*
    getTask takes in an array with the command at index 0 and the taskID in index 1
    returns the Task object with the corresponding taskID if ID is valid.
     */
    private Task getTask(String[] arr) {

        int taskNum;
        try {
            String secondParam;
            secondParam = arr[1];
            taskNum = Integer.parseInt(secondParam);
        }
        //Possible error: No input value behind "done" command
        catch (IndexOutOfBoundsException e) {
            dukeEcho("Please enter a task ID.");
            return null;
        }
        //Possible error: Task ID not in numerical format
        catch (NumberFormatException e) {
            dukeEcho("Please enter a valid numerical task ID.");
            return null;
        }

        Task currTask;
        try {
            currTask = _tasks.get(taskNum-1);
        }
        //Possible error: Task ID out of bounds
        catch (IndexOutOfBoundsException e) {
            dukeEcho("Please ensure task ID is between 1 and " + _tasks.size());
            return null;
        }

        return currTask;
    }


    private void listTasks() {
        System.out.println(DIVIDER);
        System.out.println(BLANKSPACE + "Here are the tasks in your list:");
        for (int i = 0; i < _tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + _tasks.get(i));
        }
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        Duke lvl6 = new Duke();
        lvl6.run();
    }
}