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

            try {
                Task task;
                switch (arguments[0]) {
                    case "bye":
                        printExitMsg();
                        System.exit(0);
                        break;
                    case "list":
                        printList(taskList);
                        break;
                    case "done":
                        int num = Integer.parseInt(arguments[1]);
                        if (num < 1 || num > taskList.getNumTasks()) {
                            throw new IndexOutOfBoundsException();
                        }
                        task = taskList.getTaskByIndex(num);
                        task.markAsDone();
                        printDoneMsg(task);
                        break;
                    case "deadline":
                    case "event":
                    case "todo":
                        if (arguments.length == 1) {
                            throw new DukeException(String.format(" ☹ OOPS!!! The description of %s %s cannot be empty.", arguments[0].equals("event") ? "an" : "a", arguments[0]));
                        }
                        int index = -1;
                        if (arguments[0].equals("deadline")) {
                            index = findIndexByToken(arguments, "/by");
                            if (index == -1) {
                                throw new DukeException(" ☹ OOPS!!! /by must be present for a deadline.");
                            }
                        } else if (arguments[0].equals("event")){
                            index = findIndexByToken(arguments, "/at");
                            if (index == -1) {
                                throw new DukeException(" ☹ OOPS!!! /at must be present for an event.");
                            }
                        }
                        if (arguments[0].equals("todo")) {
                            String description = parseArguments(arguments, 1, arguments.length);
                            task = new TodoTask(description);
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
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printException(e.getMessage());
            } catch (NumberFormatException e) {
                printException(" ☹ OOPS!!! Please enter an integer after done.");
            } catch (IndexOutOfBoundsException e) {
                printException(" ☹ OOPS!!! There is no such task with this index, please try another index.");
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

    //prints one section(bounded by lines)
    private static void printSection(String msg) {
        printLine();
        printIndentation();
        System.out.println(" " + msg);
        printLine();
        System.out.println();
    }

    //prints one section(bounded by lines) with multiple lines of messages
    private static void printSection(String[] msgArray) {
        printLine();
        for (String string : msgArray) {
            printIndentation();
            System.out.println(string);
        }
        printLine();
        System.out.println();
    }

    private static void printIntro() {
        String[] array = new String[2];
        array[0] = " Hello! I'm Duke";
        array[1] = " What can I do for you?";
        printSection(array);
    }

    private static void printExitMsg() {
        printSection(" Bye. Hope to see you again soon!");
    }

    private static void printAddTaskMsg(Task task) {
        String[] array = new String[3];
        array[0] = " Got it. I've added this task: ";
        array[1] = String.format("   %s", task);
        array[2] = String.format(" Now you have %d %s in the list.",taskList.getNumTasks(), taskList.getNumTasks() == 1 ? "task" : "tasks");
        printSection(array);
    }

    private static void printList(MyList myList) {
        List<Task> list = myList.getList();
        int listNum = 1;

        String[] array = new String[myList.getNumTasks() + 1];
        array[0] = " Here are the tasks in your list:";
        for (Task task : list) {
            array[listNum] = String.format(" %d.%s", listNum, task);
            listNum++;
        }
        printSection(array);
    }

    private static void printDoneMsg(Task task) {
        String[] array = new String[2];
        array[0] = " Nice! I've marked this task as done: ";
        array[1] = String.format("   %s", task);
        printSection(array);
    }

    private static void printException(String msg) {
        printSection(msg);
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
