import java.util.Scanner;

import java.util.ArrayList;

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
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            //break before storing task if input = bye, add done to number
            if (input.equals("bye")){
                Duke.exit();
                break;
            } else if (input.equals("done")){
                int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
                // close if item number don't exist
                if ((itemIndex + 1) > Duke.storedTasks.size()){
                    System.out.println("failed to find item, closing now.");
                    Duke.exit();
                } else {
                    Duke.done(itemIndex);
                }
                continue;
            }
            //store task if input != "bye"
            if (input.equals("list")){
                for(int i = 0; i < Duke.storedTasks.size(); i++){
                    System.out.print((i + 1) + ".");
                    System.out.print("[" + Duke.storedTasks.get(i).getStatusIcon() + "] ");
                    System.out.println(Duke.storedTasks.get(i).getDescription());
                    continue;
                }
            } else {
                Task task = new Task(input);
                Duke.storedTasks.add(task);
                System.out.println("added: " + input);
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
        System.out.println("  " + "[" + Duke.storedTasks.get(itemNum).getStatusIcon()
                + "] " + Duke.storedTasks.get(itemNum).getDescription());
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
