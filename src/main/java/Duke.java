import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //intialize an arraylist to store strings
        List<Task> store = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        String argument = myScanner.nextLine();
        //when user input is not bye
        while (!argument.equals("bye") && !argument.equals("Bye")) {
            if (argument.equals("list")) {
                int num = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task i : store) {
                    System.out.println(+ num + ". " + i);
                    num++;
                }
                argument = myScanner.nextLine();
            } else {
                String[] argumentArray = argument.split(" ");
                if (argumentArray[0].equals("done")) {
                    int index = Integer.valueOf(argumentArray[1]) - 1;
                    Task taskToModify = store.get(index);
                    taskToModify.markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(taskToModify);
                    argument = myScanner.nextLine();

                } else {
                    Task incomingTask = new Task(argument);
                    store.add(incomingTask);
                    System.out.println("added: " + argument);
                    argument = myScanner.nextLine();
                }
            }
        }

        //exiting program
        System.out.println("Bye. Hope to see you again soon!");
        myScanner.close();
    }


}
