import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public enum Action {
        ADD,
        REMOVE,
        DONE
    }


    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);

        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        String input = "";
        String firstWord;
        int taskNumber;
        String by;
        String desc;
        String at;


        try {
            FileReading.checkFileExists(taskList);
        }  catch (IOException e) {
            e.printStackTrace();
        }

        while (!input.equals("bye")) {
            input = scan.nextLine();
            firstWord = input.split(" ")[0];

            switch (firstWord) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;

            case "todo":
                try {
                    desc = input.split(" ", 2)[1];
                } catch (IndexOutOfBoundsException err) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.\n");
                    break;
                }
                ToDo toDo = new ToDo(desc);
                ModifyTaskList.modifyTaskList(taskList, toDo, Action.ADD);
                break;


            case "deadline":
                try {
                    by = input.split(" /by ")[1];
                    desc = input.split(" /by ")[0].split(" ", 2)[1];
                } catch (IndexOutOfBoundsException err) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.\n");
                    break;
                }
                Deadline d = new Deadline(desc, by);
                ModifyTaskList.modifyTaskList(taskList, d, Action.ADD);
                break;

            case "event":
                try {
                    at = input.split(" /at ")[1];
                    desc = input.split(" /at ")[0].split(" ", 2)[1];
                } catch (IndexOutOfBoundsException err) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.\n");
                    break;
                }
                Event e = new Event(desc, at);
                ModifyTaskList.modifyTaskList(taskList, e, Action.ADD);
                break;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int a = 0; a < taskList.size(); a++) {
                    System.out.println((a + 1) + ". " + taskList.get(a).toString());
                }
                System.out.println();
                break;

            case "done":
                try {
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException err) {
                    System.out.println("OOPS!!! Please enter a valid number\n");
                    break;
                } catch (IndexOutOfBoundsException err) {
                    System.out.println("You only have " + taskList.size() + " tasks, please choose a number from that\n");
                    break;
                }
                ModifyTaskList.modifyTaskList(taskList, taskNumber - 1, Action.DONE);
                break;

            case "delete":
                try {
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException err) {
                    System.out.println("OOPS!!! Please enter a valid number\n");
                    break;
                } catch (IndexOutOfBoundsException err) {
                    System.out.println("You only have " + taskList.size() + " tasks, please choose a number from that\n");
                    break;
                }
                ModifyTaskList.modifyTaskList(taskList, taskNumber - 1, Action.REMOVE);
                break;

            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means\n");
                break;
            }
        }
    }
}
