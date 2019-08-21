import java.util.List;
import java.util.Scanner;

//Assumption: reads word by word, additional arguments would be ignored
// Accepts empty task
public class Duke {
    private static MyList taskList;

    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        taskList = new TaskList();

        printLogo();
        printIntro();

        do {
            if (!sc.hasNextLine()) {
                continue;
            }

            input = sc.nextLine();
            String[] arguments = input.split(" ");

            switch (arguments[0]) {
                case "bye":
                    printExitMsg();
                    System.exit(0);
                    break;
                case "list":
                    printList(taskList);
                    break;
                case "done":
                    try {
                        int num = Integer.parseInt(arguments[1]);
                        if (num < 1 || num > taskList.getNumTasks()) {
                            throw new IndexOutOfBoundsException();
                        }
                        Task task = taskList.getTaskByIndex(num);
                        task.markAsDone();
                        printDoneMsg(task);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter an integer after done.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No such task, please try another number.");
                    }
                    break;
                case "deadline":
                case "event":
                case "todo":
                    int index = -1;
                    Task task;
                    if (arguments[0].equals("deadline")) {
                        index = findIndexByToken(arguments, "/by");
                    } else if (arguments[0].equals("event")){
                        index = findIndexByToken(arguments, "/at");
                    }
                    if (arguments[0].equals("todo")) {

                        String description = parseArguments(arguments, 1, arguments.length);
                        task = new TodoTask(description);
                    } else if (index == -1) {
                        System.out.println("Time not specified. Try again.");
                        break;
                    } else {
                        String description = parseArguments(arguments, 1, index);
                        String time = parseArguments(arguments, index + 1, arguments.length);
                        if (arguments[0].equals("deadline")) {
                            task = new DeadlineTask(description, time);
                        } else {
                            task = new EventTask(description, time);
                        }
                    }
                    taskList.add(task);
                    printAddTaskMsg(task);
                    break;
                default:
                    System.out.println("Invalid Command, try again.");
                    break;
            }
        } while (!input.equals("bye"));
    }

    //prints the logo for the chat bot
    private static void printLogo() {
        System.out.println(Consts.LOGO);
    }

    //prints the indentation used for the output
    private static void printIndentation() {
        System.out.print("\t");
    }

    //prints line with indentation in front
    private static void printLine() {
        printIndentation();
        System.out.println("____________________________________________________________");
    }

    //prints the intro text
    private static void printIntroText() {
        printIndentation();
        System.out.println(" Hello! I'm Duke");
        printIndentation();
        System.out.println(" What can I do for you?");
    }

    private static void printIntro() {
        printLine();
        printIntroText();
        printLine();
        System.out.println();
    }

    private static void printExitMsg() {
        printLine();
        printIndentation();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printAddTaskMsg(Task task) {
        printLine();
        printIndentation();
        System.out.println(" Got it. I've added this task: ");
        printIndentation();
        System.out.printf("   %s\n", task);
        printIndentation();
        System.out.printf(" Now you have %d %s in the list.\n",taskList.getNumTasks(), taskList.getNumTasks() == 1 ? "task" : "tasks");
        printLine();
        System.out.println();
    }

    private static void printList(MyList myList) {
        List<Task> list = myList.getList();
        int listNum = 1;
        printLine();
        printIndentation();
        System.out.println(" Here are the tasks in your list:");
        for (Task task : list) {
            printIndentation();
            System.out.printf(" %d.%s\n", listNum, task);
            listNum++;
        }
        printLine();
        System.out.println();
    }

    private static void printDoneMsg(Task task) {
        printLine();
        printIndentation();
        System.out.println(" Nice! I've marked this task as done: ");
        printIndentation();
        System.out.printf("   %s\n", task);
        printLine();
        System.out.println();
    }

    //returns index of token if found, else returns -1
    //stops at first occurrence of token
    private static int findIndexByToken(String[] array, String token) {
        int index = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(token)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Combines words into a sentence
    private static String parseArguments(String[] array, int start, int end) {
        if (start >= end) {
            return "";
        }

        StringBuilder result = new StringBuilder(array[start]);
        for (int i = start + 1; i < end; i++) {
            result.append(" ").append(array[i]);
        }
        return result.toString();
    }
}
