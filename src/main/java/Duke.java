import java.util.List;
import java.util.Scanner;

//Assumption: reads word by word, additional arguments would be ignored
public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        MyList taskList = new TaskList();

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
                default:
                    taskList.add(new Task(input));
                    printAddTask(input);
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

    private static void printAddTask(String task) {
        printLine();
        printIndentation();
        System.out.printf(" added: %s\n", task);
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
            System.out.printf(" %d.[%s] %s\n", listNum, task.getStatusIcon(), task.getDescription());
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
        System.out.printf("   [%s] %s\n", task.getStatusIcon(), task.getDescription());
        printLine();
        System.out.println();

    }
}
