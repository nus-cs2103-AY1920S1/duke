import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        String[] splitInput;
        boolean dukeIsOn = true;
        ToDoList myTasks = new ToDoList();
        while(dukeIsOn){
            input = sc.nextLine();
            splitInput = input.split(" ");
            switch (splitInput[0]) {
                case "list":
                    myTasks.listAllTasks();
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    dukeIsOn = false;
                    break;
                case "done":
                    myTasks.checkTask(Integer.parseInt(splitInput[1]) - 1);
                    break;
                case "todo":
                    myTasks.addTask(input.substring(5), TaskType.TODO);
                    break;
                case "deadline":
                    myTasks.addTask(input.substring(9), TaskType.DEADLINE);
                    break;
                case "event":
                    myTasks.addTask(input.substring(6), TaskType.EVENT);
                    break;
                default:
                    System.out.println("error in input");
                    break;
            }
        }
    }
}
