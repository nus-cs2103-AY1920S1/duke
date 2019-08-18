import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<Task> taskList = new ArrayList<Task>();

    private static final String indentLine = "---------------------------------------------";
    private static final String introMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String goodbyeMessage = "Bye. Hope to see you again soon!";
    private static final String doneMessage = "Nice! I've marked this task as done:";

    public static void main(String[] args) {

        printIntroMessage();

        while(true) {

            String input = sc.nextLine();

            if(isBye(input)) {
                printGoodbyeMessage();
                break;
            }

            if(isList(input)) {
                printList();
            } else if(input.startsWith("done")) {
                String[] stringArray = input.split(" ");

                int taskNum = Integer.parseInt(stringArray[1]);

                Task task = taskList.get(taskNum - 1);

                task.setDone();

                printDoneMessage(task);
            }
            else {
                addToList(input);
                printInputConfirmation(input);
            }

        }

    }

    private static void printIntroMessage() {
        System.out.println(indentLine);

        System.out.println(introMessage);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    private static void printGoodbyeMessage() {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(goodbyeMessage);

        System.out.println();

        System.out.println(indentLine);
    }

    private static void printInputConfirmation(String input) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println("added: " + input);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    private static void printDoneMessage(Task task) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(doneMessage);

        System.out.println("    " + task);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    private static void printList() {

        int size = taskList.size();

        System.out.println();

        System.out.println(indentLine);

        for(int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    private static void addToList(String input) {
        Task task = new Task(input);
        taskList.add(task);
    }

    private static boolean isBye(String input) {
        return input.equals("bye") || input.equals("Bye");
    }

    private static boolean isList(String input) {
        return input.equals("list");
    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);