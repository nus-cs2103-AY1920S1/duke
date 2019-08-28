import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static void main(String[] args) {
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
                Task completedTask = tasks[completedTaskNum];
                printAndEvaluateTaskDone(completedTask);
                break;
            case "event":
                Event newEvent = parseAndEvaluateEvent(newTaskSplit);
                addEvent(newEvent);
                break;
            case "deadline":
                Deadline newDeadline = parseAndEvaluateDeadline(newTaskSplit);
                addDeadline(newDeadline);
                break;
            case "todo":
                ToDo newToDo = parseAndEvaluateToDo(newTaskSplit);
                addToDo(newToDo);
                break;
            default:
                addTask(newTaskLine);
                break;
            }
        }
    }

    static void addBorder(String input) {
        String border = "__________________________________________________________";
        System.out.print(border + "\n\n" + input + "\n" + border + "\n\n");
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

    static void addTask(String newTaskLine) {
        Task newTask = new Task(newTaskLine);
        tasks[tasksCount] = newTask;
        String output = "added: " + newTask.toString();
        addBorder(output);
        tasksCount++;
    }

    static ToDo parseAndEvaluateToDo(String[] newTaskSplit) {
        int newTaskLen = newTaskSplit.length;
        if (newTaskLen < 2) {
            System.out.println("Error");
            return null;
        } else {
            String description = newTaskSplit[1];
            for (int i = 2; i < newTaskLen; i ++) {
                description += " " + newTaskSplit[i];
            }
            return new ToDo(description);
        }
    }
    static void addToDo(ToDo newToDo) {
        tasks[tasksCount] = newToDo;
        tasksCount++;
        String output = "Got it. I've added this task:\n"
                + newToDo.toString()
                + "\nNow you have " + tasksCount + " tasks in the list.";
        addBorder(output);
    }

    static Deadline parseAndEvaluateDeadline(String[] newTaskSplit) {
        int newTaskLen = newTaskSplit.length;
        if (newTaskLen < 3) {
            System.out.println("Error");
            return null;
        } else {
            boolean foundDeadline = false;
            boolean isFirstDeadlineWord = true;
            int index = 1;
            String description = newTaskSplit[index];
            String by = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundDeadline) {
                    if (i == newTaskLen - 1) {
                        by += newTaskSplit[i];
                    } else {
                        by += newTaskSplit[i] + " ";
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
                return new Deadline(description, by);
            } else {
                System.out.println("Error");
                return null;
            }
        }
    }

    static void addDeadline(Deadline newDeadline) {
        tasks[tasksCount] = newDeadline;
        tasksCount++;
        String output = "Got it. I've added this task:\n"
                + newDeadline.toString()
                + "\nNow you have " + tasksCount + " tasks in the list.";
        addBorder(output);
    }

    static Event parseAndEvaluateEvent(String[] newTaskSplit) {
        int newTaskLen = newTaskSplit.length;
        if (newTaskLen < 3) {
            System.out.println("Error");
            return null;
        } else {
            boolean foundEvent = false;
            int index = 1;
            String description = newTaskSplit[index];
            String at = "";
            for (int i = 2; i < newTaskLen; i++) {
                if (foundEvent) {
                    if (i == newTaskLen - 1) {
                        at += newTaskSplit[i];
                    } else {
                        at += newTaskSplit[i] + " ";
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
                return new Event(description, at);
            } else {
                System.out.println("Error");
                return null;
            }
        }
    }

    static void addEvent(Event newEvent) {
        tasks[tasksCount] = newEvent;
        tasksCount++;
        String output = "Got it. I've added this task:\n"
                + newEvent.toString()
                + "\nNow you have " + tasksCount + " tasks in the list.";
        addBorder(output);
    }

    static void printList() {
        String str = "";

        for (int i = 1; i < tasksCount + 1; i++) {
            String newTask = tasks[i-1].toString();
            if (i == tasksCount) {
                str += i + ". " + newTask;
            } else {
                str += i + ". " + newTask + "\n";
            }
        }

        addBorder(str);
    }

}