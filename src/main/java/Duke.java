import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> taskList = new ArrayList<>();

        String logo = "____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        printMessage(" " + logo + "\n\t Hello! I'm Duke" + "\n" + "\t What can I do for you?");

        while(sc.hasNext()) {
            String input = sc.nextLine();

            if(input.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                return;
            } else if(input.equals("list")) {
                printTaskList(taskList);
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                printMessage("added: " + input);
            }
        }

    }

    public static void printMessage(String output) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + output);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void printTaskList(List<Task> taskList) {
        System.out.println("\t____________________________________________________________");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + " " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }
}


