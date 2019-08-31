//did not account for inputs with space before taskType. "   todo" smart

import java.util.ArrayList;
import java.util.Scanner;

enum TaskType {
    TODO,
    EVENT,
    DEADLINE,
    LIST,
    DONE,
    BYE,
    DELETE
}

public class Duke {
    public static void main(String[] args) {
        User user = new User();
        Scanner input = new Scanner(System.in);

        boolean inputErrors;
        do {
            try {
                user.setCurrentInput(input.nextLine());
                if (!user.setTaskType()) {
                    throw new UnknownInputException();
                }
                TaskType taskType = user.getTaskType();
                if (user.oneWordNotBye()) {
                    throw new BadInputException(taskType);
                }

                switch (taskType) {
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
                case DELETE:
                    user.deleteTask();
                    break;
                case BYE: //do nothing and wait to exit loop
                    break;
                default:
                    break;
                }
                inputErrors = true;
            } catch (UnknownInputException | BadInputException err) {
                inputErrors = false;
            }
        } while (!user.inputIsBye() || !inputErrors); //only exit program, when user inputs bye. otherwise keep trying

        user.sayByeToUser();
    }
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

    void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }

    String getCurrentInput () {
        return this.currentInput;
    }

    boolean setTaskType() {
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
        case "delete":
            this.currentTaskType = TaskType.DELETE;
            break;
        default:
            return false; //invalid task
        }
        return true;
    }

    void addTask() { //addTask/event.deadline
        //todo: first space to last char
        //event/deadline: first space to "/", after "/" to last char
        TaskType taskType = this.getTaskType(); //used just for switchcase.
        //only description and date gets passed into subclass of Tasks
        String j = this.getCurrentInput();
        String description = "";
        String date = "";

        switch (taskType) {
        case DEADLINE:
            description = j.substring(j.indexOf(" ") + 1, j.indexOf("/") - 1);
            date = j.substring((j.indexOf("/") + 4));
            userTasks.add(new Deadline(description, date));
            break;
        case EVENT:
            description = j.substring(j.indexOf(" ") + 1, j.indexOf("/") - 1);
            date = j.substring((j.indexOf("/") + 4));
            userTasks.add(new Event(description, date));
            break;
        case TODO:
            description = j.substring(j.indexOf(" ") + 1);
            userTasks.add(new ToDo(description));
            break;
        default:
            break;
        }

        this.increaseTaskCount();

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

    public TaskType getTaskType () {
        return this.currentTaskType;
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
                        + "     Nice! I've marked this task as done: \n" + "       [" + task.getTaskTypeLetter()
                        + "][" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                        + ("    ____________________________________________________________\n"));
            }
            count++;
        }
    }

    private void increaseTaskCount() {
        this.numOfTasks++;
    }

    private void decreaseTaskCount () {
        if (this.numOfTasks >= 0) {
            this.numOfTasks--;
        }
    }

    public void deleteTask() {
        /*print task, delete task, decrease task count, then declare new task count. */
        String j = this.getCurrentInput();
        int taskNum = Integer.parseInt(j.substring(j.indexOf(" ")+ 1)) - 1;
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task: ");
        int count = 0;
        for (Task temp : userTasks) {
            if (count == taskNum) {
                System.out.println("       " + "[" + temp.getTaskTypeLetter() + "]"
                        + "[" + temp.getStatusIcon() + "] " + temp.getDescription());
            }
            count++;
        }

        userTasks.remove(taskNum);
        this.decreaseTaskCount();
        System.out.println("     Now you have " + this.getTaskCount() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    boolean oneWordNotBye() {
        String s = this.getCurrentInput();
        if (s.equalsIgnoreCase("bye") || s.equalsIgnoreCase("list")) { //bye and list should not be checked
            return false;
        } else {
            return (s.length() > 0 && s.split("\\s+").length == 1);
        }
    }

    public boolean inputIsBye() {
        String EXIT_COMMAND = "bye";
        return this.currentInput.equalsIgnoreCase(EXIT_COMMAND);
    }

    public void printUserInputs () { //catch empty list?
        int count = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task temp : userTasks) {
            System.out.println("       " + count + ".[" + temp.getTaskTypeLetter() + "]"
                    + "[" + temp.getStatusIcon() + "] " + temp.getDescription());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void sayByeToUser () {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}

class UnknownInputException extends Exception {
    public UnknownInputException() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________");
    }
}

class BadInputException extends Exception {
    public BadInputException(TaskType temp) {
        String i = "";
        switch(temp) {
        case TODO:
            i = "todo";
            break;
        case EVENT:
            i = "event";
            break;
        case DEADLINE:
            i = "deadline";
            break;
        case DONE:
            i = "done";
            break;
        case DELETE:
            i = "delete";
            break;
        }
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a " + i + " cannot be empty.\n" +
                "    ____________________________________________________________\n");
    }
}
