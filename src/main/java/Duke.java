import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> inputList = new LinkedList<>();

        greet();
        String input = sc.next();

        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                if (!input.equals("done")) {
                    if (input.equals("todo")) {
                        input = sc.nextLine();
                        Task newTodo = new Todo(input);
                        Task.addTask(newTodo);

                        input = sc.next();

                    } else if (input.equals("deadline")) {
                        input = sc.nextLine();
                        String[] inputArr = input.split(" /");

                        Task newDeadline = new Deadline(inputArr[0], inputArr[1]);

                        Task.addTask(newDeadline);

                        input = sc.next();

                    } else {
                        input = sc.nextLine();
                        String[] inputArr = input.split(" /");

                        Task newEvent = new Event(inputArr[0], inputArr[1]);

                        Task.addTask(newEvent);

                        input = sc.next();
                    }
                } else {
                    int taskNum = sc.nextInt();
                    Task.doTask(taskNum);

                    input = sc.next();
                }
            } else {
                Task.printTaskList();

                sc.nextLine();
                input = sc.next();
            }
        }

        exit();
    }

    public static void printLine() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 60; i++) {
            line.append("_");
        }

        String stringLine = line.toString();
        printIndent();
        System.out.println(stringLine);
    }

    public static void printIndent() {
        System.out.print("    ");
    }

    public static void greet() {
        String greeting = "Hello! I'm Duke\n" +
                "    What can I do for you?";

        printLine();

        printIndent();
        System.out.println(greeting);

        printLine();
    }

    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon";

        printLine();

        printIndent();
        System.out.println(exitMessage);

        printLine();
    }


}