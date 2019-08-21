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

        ArrayList<String> toDoList = new ArrayList<>();

        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?");

        String command = promptEntry();


        while (!command.equals("bye")) {


            switch (command) {

                case "list":
                    printList(toDoList);
                    break;

                default:
                    toDoList.add(command);
                    System.out.println("added: " + command);
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
       return sc.nextLine();
    }

    public static void printList(ArrayList<String> toDoList) {
        int n = 1;

        if(toDoList.isEmpty()){

            System.out.println("List is empty");

        } else {

            for (String item : toDoList) {
                System.out.println(n + ". " + item);
                n++;
            }

        }
    }
}
