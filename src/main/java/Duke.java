import java.lang.reflect.Array;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    //variables
    public static ArrayList<Task> storedTasks = new ArrayList<>();

    //main
    public static void main(String[] args) {
        Duke.greet();
        readInput();
    }

    //implementation methods
    public static void readInput(){
        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
//            System.out.println(input.split("/at ")[1]);
            //break before storing task if input = bye, add done to number
            if (input.equals("bye")){
                Duke.exit();
                break;
            } else if ((input.length() > 5) && input.substring(0, 5).equals("done ")){
                int itemIndex = Integer.parseInt(input.substring(5)) - 1;
                // close if item number don't exist
                if ((itemIndex + 1) > Duke.storedTasks.size()){
                    System.out.println("failed to find item, closing now.");
                    Duke.exit();
                    break;
                } else {
                    Duke.done(itemIndex);
                }
                continue;
            }
            //store task if input != "bye"
            if (input.equals("list")){
                Duke.showList();
            } else {
                //handles the todo, deadline and event tasks
                System.out.println("Got it. I've added this task:");
                if(input.substring(0,5).equals("todo ")){
                    String inputAdd = input.substring(5);
                    Todo todo = new Todo(inputAdd, "by");
                    Duke.storedTasks.add(todo);
                    System.out.println("  " + todo);
                } else if(input.substring(0,9).equals("deadline ")){
                    String inputAddArr[] = input.split(" /by ");
                    String inputAdd1 = inputAddArr[0].substring(9);
                    String inputAdd2 = inputAddArr[1];
                    Deadline deadline = new Deadline(inputAdd1, inputAdd2);
                    Duke.storedTasks.add(deadline);
                    System.out.println("  " + deadline);
                } else if(input.substring(0,6).equals("event ")){
                    String inputAddArr[] = input.split(" /at ");
                    String inputAdd1 = inputAddArr[0].substring(6);
                    String inputAdd2 = inputAddArr[1];
                    Event event = new Event(inputAdd1, inputAdd2);
                    Duke.storedTasks.add(event);
                    System.out.println("  " + event);
                }
                System.out.println("Now you have " + storedTasks.size()
                        + " tasks in the list.");
            }
        }
    }

    public static void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(String input){
        System.out.println(input);
    }

    public static void done(int itemNum){
        Duke.storedTasks.get(itemNum).doneJob();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + Duke.storedTasks.get(itemNum));
    }

    public static void showList(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < Duke.storedTasks.size(); i++){
            System.out.print((i + 1) + ".");
            System.out.println(Duke.storedTasks.get(i));
            continue;
        }
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
