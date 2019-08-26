import java.io.File;
import java.io.FileWriter;

import java.util.Scanner;

import java.util.LinkedList;
import java.util.ListIterator;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
            run();
    }

    public static void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> inputList = new LinkedList<>();

        File dukeTaskList = new File("dukeTaskList.txt");
        TaskFile fileUpdater = new TaskFile(dukeTaskList);
        fileUpdater.checkForExistingTasks(dukeTaskList);

        greet();
        String input = sc.nextLine();

        while (true) {
            try {
                String[] inputArr = input.split(" ");
                String taskType = inputArr[0];
                String taskDesc = combine(inputArr);

                if (!correctInput(taskType)) {
                    throw new IncorrectInputException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                if (taskType.equals("bye")) {
                    break;
                }

                if (taskType.equals("list")) {

                    Task.printTaskList();
                    input = sc.nextLine();

                } else if (taskType.equals("done")) {

                    int taskNum = Integer.parseInt(taskDesc);
                    Task.doTask(taskNum);

                    fileUpdater.update();
                    input = sc.nextLine();

                } else if (taskType.equals("delete")) {

                    int taskNum = Integer.parseInt(taskDesc);
                    Task.deleteTask(taskNum);

                    fileUpdater.update();
                    input = sc.nextLine();

                } else if (inputArr.length == 1){

                    throw new NoDescriptionException(":( OOPS!!! The description of " + inputArr[0] + " cannot be empty.");

                } else if (taskType.equals("todo")) {

                    Task newTodo = new Todo(taskDesc);
                    Task.addTask(newTodo);

                    fileUpdater.update();
                    input = sc.nextLine();

                } else if (taskType.equals("deadline")) {

                    String[] taskDescArr = taskDesc.split(" /");

                    Task newDeadline = new Deadline(taskDescArr[0], taskDescArr[1]);

                    Task.addTask(newDeadline);

                    fileUpdater.update();
                    input = sc.nextLine();

                } else {
                    String[] taskDescArr = taskDesc.split(" /");

                    Task newEvent = new Event(taskDescArr[0], taskDescArr[1]);

                    Task.addTask(newEvent);

                    fileUpdater.update();
                    input = sc.nextLine();
                }


            } catch (DukeException e) {
                printLine();

                printIndent();
                System.out.println(e.getMessage());

                printLine();

                input = sc.nextLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        exit();
    }

    public static boolean correctInput(String input) {
        if (input.equals("todo") ||
                input.equals("event") ||
                input.equals("deadline") ||
                input.equals("list") ||
                input.equals("done") ||
                input.equals("bye") ||
                input.equals("delete")) {
            return true;
        } else {
            return false;
        }
    }

    public static String combine(String[] inputArr) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < inputArr.length; i++) {
            if (i != inputArr.length - 1) {
                builder.append(inputArr[i]);
                builder.append(" ");
            } else {
                builder.append(inputArr[i]);
            }
        }

        return builder.toString();
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