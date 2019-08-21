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
        };

        String taskDetails;
        Task newTask;

        //Possible exception: no parameters for task name and detail
        try {
            taskDetails = arr[1];
        } catch (IndexOutOfBoundsException e) {
            dukeEcho("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
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

        int taskNum;

        try {
            String secondParam;
            secondParam = arr[1];
            taskNum = Integer.parseInt(secondParam);
        }
        //Possible exception: No input value behind "done" command
        catch (IndexOutOfBoundsException e) {
            dukeEcho("Please enter a task ID.");
            return;
        }
        //Possible exception: Task ID not in numerical format
        catch (NumberFormatException e) {
            dukeEcho("Please enter a valid numerical task ID.");
            return;
        }

        Task currTask;
        try {
            currTask = _tasks.get(taskNum-1);
        }
        //Possible exception: Task ID out of bounds
        catch (IndexOutOfBoundsException e) {
            dukeEcho("Please ensure task ID is between 1 and " + _tasks.size());
            return;
        }

        currTask.finishTask();
        dukeEcho("Nice! I've marked this task as done:", currTask.toString());


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
        Duke lvl4 = new Duke();
        lvl4.run();
    }
}