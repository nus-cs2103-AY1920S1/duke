import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<Task> toDoList = new ArrayList<>();

        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?");

        String command = promptEntry();



        while (!command.equals("bye")) {


            switch (command) {

                case "list":
                    printList(toDoList);
                    break;

                case "done":
                    int taskNumber = sc.nextInt()-1;
                    completeTask(taskNumber, toDoList);
                    break;
                default:
                    String wholeCommand = command + sc.nextLine();
                    Task newTask = new Task(wholeCommand);
                    toDoList.add(newTask);
                    System.out.println("added: " + wholeCommand);
                    break;

            }
            command = promptEntry();

        }
        printCommand("Bye. Hope to see you again soon!");

    }





    public static void printCommand(String command) {
        System.out.println(command);
    }

    public static String promptEntry() {
       return sc.next();
    }

    public static void printList(ArrayList<Task> toDoList) {
        int n = 1;

        if(toDoList.isEmpty()){

            System.out.println("List is empty");

        } else {

            for (Task item : toDoList) {
                System.out.println(n + ". " + item.getStatusIcon() + " " + item);
                n++;
            }

        }
    }

    public static void completeTask(int taskNumber, ArrayList<Task> list) {
        Task completed = list.get(taskNumber);

            completed.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + "   " + completed.getStatusIcon() + " " + completed);

    }
}
