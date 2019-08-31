import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String indent = "    ";

    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printGreetingMessage();

        while(sc.hasNextLine()) {
            String newTaskLine = sc.nextLine();
            String[] newTaskSplit = newTaskLine.split(" ");
            String taskType = newTaskSplit[0];

            switch(taskType) {
            case "list":
                printList();
                break;
            case "bye":
                printExitMessage();
                return;
            case "done":
                int completedTaskNum = Integer.parseInt(newTaskSplit[1]) - 1;
                Task completedTask = tasks.get(completedTaskNum);
                printAndEvaluateTaskDone(completedTask);
                break;
            case "event":
                try {
                    parseAndEvaluateEvent(newTaskSplit);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    parseAndEvaluateDeadline(newTaskSplit);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            case "todo":
                try {
                    parseAndEvaluateToDo(newTaskSplit);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            case "delete":
                try {
                    parseAndEvaluateDelete(newTaskSplit);
                } catch (DukeException e) {
                    addBorder(e.getMessage());
                }
                break;
            default:
                addBorder(new DukeIllegalArgumentException().getMessage());
                break;
            }
        }
    }

    static void addBorder(String input) {
        String border = "____________________________________________________________";
        System.out.print(border + "\n" + input + "\n" + border + "\n\n");
    }

    static void printGreetingMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    static void printAndEvaluateTaskDone(Task completedTask) {
        completedTask.taskComplete();
        addBorder("Nice! I've marked this task as done: \n" + completedTask.toString());
    }

    static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

    //Old unused method for adding generic tasks
    /*
    static void addTask(String newTaskLine) {
        Task newTask = new Task(newTaskLine);
        tasks[tasksCount] = newTask;
        String output = "added: " + newTask.toString();
        addBorder(output);
        tasksCount++;
    }
    */

    static void parseAndEvaluateDelete(String[] newTaskSplit) throws DukeException {
        int newTaskLen = newTaskSplit.length;
        if (newTaskLen < 2) {
            throw new DukeDeleteIllegalArgumentException("You have not entered a number for deletion");
        } else if (newTaskLen > 2) {
            throw new DukeDeleteIllegalArgumentException("You have entered too many arguments for deletion");
        } else {
            try {
                int deletionNum = Integer.parseInt(newTaskSplit[1]) - 1;
                deleteTask(deletionNum);
            } catch (NumberFormatException e) {
                throw new DukeDeleteIllegalArgumentException("Please enter a valid number for deletion");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeDeleteIllegalArgumentException("Please enter a valid number within the range");
            }
        }
    }

    static void deleteTask(int deletionNum) throws IndexOutOfBoundsException {
        Task deletedTask = tasks.remove(deletionNum);
        String output = "Noted. I've removed this task: \n"
                + deletedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.\n";
        addBorder(output);
    }

    static void parseAndEvaluateToDo(String[] newTaskSplit) throws DukeException {
        try {
            int newTaskLen = newTaskSplit.length;
            String description = newTaskSplit[1];
            for (int i = 2; i < newTaskLen; i++) {
                description += " " + newTaskSplit[i];
            }
            addToDo(new ToDo(description));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeToDoIllegalArgumentException();
        }
    }
    static void addToDo(ToDo newToDo) {
        tasks.add(newToDo);
        String output = "Got it. I've added this task:\n"
                + newToDo.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        addBorder(output);
    }

    static void parseAndEvaluateDeadline(String[] newTaskSplit) throws DukeException {
        try {
            int newTaskLen = newTaskSplit.length;
            boolean foundDeadline = false;
            String description = newTaskSplit[1];
            String deadlineTime = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundDeadline) {
                    if (i == newTaskLen - 1) {
                        deadlineTime += newTaskSplit[i];
                    } else {
                        deadlineTime += newTaskSplit[i] + " ";
                    }
                } else {
                    if (newTaskSplit[i].equals("/by")) {
                        foundDeadline = true;
                    } else {
                        description += " " + newTaskSplit[i];
                    }
                }
            }
            if (foundDeadline) {
                addDeadline(new Deadline(description, deadlineTime));
            } else {
                throw new DukeDeadlineIllegalArgumentException("deadline");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeDeadlineIllegalArgumentException("description");
        }
    }

    static void addDeadline(Deadline newDeadline) {
        tasks.add(newDeadline);
        String output = "Got it. I've added this task:\n"
                + newDeadline.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        addBorder(output);
    }

    static void parseAndEvaluateEvent(String[] newTaskSplit) throws DukeException {
        int newTaskLen = newTaskSplit.length;
        try {
            boolean foundEvent = false;
            String description = newTaskSplit[1];
            String eventTime = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundEvent) {
                    if (i == newTaskLen - 1) {
                        eventTime += newTaskSplit[i];
                    } else {
                        eventTime += newTaskSplit[i] + " ";
                    }
                } else {
                    if (newTaskSplit[i].equals("/at")) {
                        foundEvent = true;
                    } else {
                        description += " " + newTaskSplit[i];
                    }
                }
            }
            if (foundEvent) {
                addEvent(new Event(description, eventTime));
            } else {
                throw new DukeEventIllegalArgumentException("timing");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEventIllegalArgumentException("description");
        }
    }

    static void addEvent(Event newEvent) {
        tasks.add(newEvent);
        String output = "Got it. I've added this task:\n"
                + newEvent.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        addBorder(output);
    }

    static void printList() {
        String str = "";
        int numTasks = tasks.size();
        for (int i = 0; i < numTasks; i++) {
            String newTask = tasks.get(i).toString();
            if (i == numTasks - 1) {
                str += (i + 1) + ". " + newTask;
            } else {
                str += (i + 1) + ". " + newTask + "\n";
            }
        }
        addBorder(str);
    }

}