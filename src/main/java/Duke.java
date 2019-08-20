import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //hr is horizontal row
        String hr = "___________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(hr);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(hr);
        ArrayList<Task> arr = new ArrayList<>();
        while(true) {
            String command = sc.nextLine();
            System.out.println(hr);
            if (command.split(" ")[0].equals("done")){
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                Task temp = arr.get(index);
                temp.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + temp.getStatusIcon() + "] " + temp);
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.print(hr);
                break;
            } else if(command.equals("list")) {
                for(int i = 0; i < arr.size(); i++) {
                    Task temp = arr.get(i);
                    System.out.println((i + 1) + ".[" + temp.getStatusIcon() + "] " + temp);
                }
            } else {
                arr.add(new Task(command));
                System.out.println("added: " + command);
            }
            System.out.println(hr);
        }
    }
}
