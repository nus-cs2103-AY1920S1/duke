import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

enum TaskType {
    TODO,
    EVENT,
    DEADLINE,
    LIST,
    DONE,
    BYE
}

public class Duke {
    public static void main(String[] args) {
        User user = new User();
        Scanner input = new Scanner(System.in);

        do {
            user.setCurrentInput(input.nextLine());
            user.setTaskType();
            TaskType taskType = user.getTaskType();

            switch(taskType) {
            case TODO:
            case EVENT:
            case DEADLINE:
                user.addTask();
                break;
            case DONE:
                user.setTaskDone();
                break;
            case LIST:
                user.printUserInputs();
                break;
            case BYE: default:
                break; //do nothing
            }
        } while (!user.inputIsBye());
        user.sayByeToUser();
    }
}

class DukeException extends Exception {

}

class Task {
    protected String description;
    protected boolean isDone;

    protected String typeOfTask;

    public Task() {}
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    //cant see symbols in intellji. isDone ? "\u2713" : "\u2718"

    public void markIsDone() {
        this.isDone = true;
    }

    public void setDescription(String userInput) {
        this.description = userInput;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskTypeLetter() {
        return typeOfTask;
    }
}

class Deadline extends Task {
    //formatting of inputs is left in User class
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDescription() {
        return super.getDescription() + " (by: " + by + ")";
    }

    public String getTaskTypeLetter() {
        return "D";
    }
}

class Event extends Task {
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getDescription() {
        return super.getDescription() + " (at: " + at + ")";
    }

    public String getTaskTypeLetter() {
        return "E";
    }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public String getDescription() {
        return super.getDescription();
    }

    public String getTaskTypeLetter() {
        return "T";
    }
}

class User {
    private String currentInput = ""; //user input
    private TaskType currentTaskType; //enum type, after parsing user input.
    private ArrayList<Task> userTasks = new ArrayList<Task>();
    private int numOfTasks = 0;

    public User() {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");

    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }

    public void setTaskType() {
        String j = this.currentInput;
        if (j.contains(" ")) {
            j = j.substring(0, j.indexOf(" "));
            j = j.replaceAll("\\s+", "");
        }
        j = j.toLowerCase();

        switch (j) {
        case "event":
            this.currentTaskType = TaskType.EVENT;
            break;
        case "deadline":
            this.currentTaskType = TaskType.DEADLINE;
            break;
        case "todo":
            this.currentTaskType = TaskType.TODO;
            break;
        case "list":
            this.currentTaskType = TaskType.LIST;
            break;
        case "done":
            this.currentTaskType = TaskType.DONE;
            break;
        case "bye":
            this.currentTaskType = TaskType.BYE;
            break;
        default:
            break;
        }
    }

    public void addTask() { //addTask/event.deadline
        //todo: first space to last char
        //event/deadline: first space to "/", after "/" to last char
        TaskType taskType = this.getTaskType(); //used just for switchcase.
        //only description and date gets passed into subclass of Tasks
        String j = this.getCurrentInput();
        String description = "";
        String date = "";
        String parseMiddleString = "";

        switch (taskType) {
        case DEADLINE:
            description = parseMiddleString; //+1/-1 to remove spaces
            date = j.substring((j.indexOf("/") + 1));
            userTasks.add(new Deadline(description, date));
            break;
        case EVENT:
            description = parseMiddleString; //+1/-1 to remove spaces
            date = j.substring((j.indexOf("/") + 1));
            userTasks.add(new Event(description, date));
            break;
        case TODO:
            description = j.substring(j.indexOf(" ") + 1);
            userTasks.add(new ToDo(description));
            break;
        default:
            break;
        }

        //j = j.replaceAll("\\s+","");
        this.addTaskCount();

        Iterator<Task> Iterator = userTasks.iterator();

        int i = 1;
        for (Task task : userTasks) {
            if (i == this.getTaskCount()) { // new task always added to last in Array list
                System.out.println("    ____________________________________________________________\n"
                        + "     Got it. I've added this task: \n"
                        + "       [" + task.getTaskTypeLetter() + "][" + task.getStatusIcon()
                        + "] " + task.getDescription() + "\n"
                        + "     Now you have " + numOfTasks + " tasks in the list. \n" //task(s) always
                        + "    ____________________________________________________________\n");
            }
            i++;
        }
    }

    public int getTaskCount() {
        return this.numOfTasks;
    }
    private void addTaskCount() {
        this.numOfTasks++;
    }

    private void decreaseTaskCount () {
        if (this.numOfTasks >= 0) {
            this.numOfTasks--;
        }
    }

    public boolean inputIsBye () {
        String EXIT_COMMAND = "bye";
        return this.currentInput.equalsIgnoreCase(EXIT_COMMAND);
    }

    public void setTaskDone () {
        String temp;
        //get 2nd number
        temp = this.currentInput.substring(this.currentInput.indexOf(" "));
        //remove space infront of number, causing errors
        temp = temp.replaceAll("\\s+", "");

        int taskNumber = Integer.parseInt(temp);
        int count = 1;
        //getDescription to output date for event/deadline
        for (Task task : userTasks) {
            if (count == taskNumber) {
                task.markIsDone();
                System.out.println("    ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done: \n" + "       [" + task.getStatusIcon()
                        + "] " + task.getDescription() + "\n"
                        + ("    ____________________________________________________________\n"));
            }
            count++;
        }
    }

    public void printUserInputs () { //catch empty list?
        int count = 1;
        System.out.println("    ____________________________________________________________");
        for (Task temp : userTasks) {
            System.out.println("    " + count + ".[" + temp.getTaskTypeLetter() + "]"
                    + "[" + temp.getStatusIcon() + "] " + temp.getDescription());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public TaskType getTaskType () {
        return this.currentTaskType;
    }

    public String getCurrentInput () {
        return this.currentInput;
    }
    public void sayByeToUser () {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}
