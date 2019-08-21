import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static List<Task> lst = new ArrayList<>();

    public static void main(String[] args) {
        List<String> start = new ArrayList<>();


        start.add("Hello! I'm Duke");
        start.add("What can I do for you?");
    	printInput(start);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

        	String input = sc.nextLine();
            String[] tokens = input.split(" ");
            if (tokens[0].equals("bye")) {
                printEnd("Bye. Hope to see you again soon!");
                break;
            } else if (tokens[0].equals("list")) {
                printNumberList(lst);
            } else if (tokens[0].equals("done")) {
                doTask(tokens[1]);
            } else {
                Task task = new Task(input);
                printInput(task);
                lst.add(task);
            }

        }

    }

    public static void printInput(Task input) {
    	System.out.println("    ____________________________________________________________");
    	System.out.println(String.format("     added:%s",input));
    	System.out.println("    ____________________________________________________________");
    	System.out.println();

    }

    public static void printInput(List<String> start) {
        System.out.println("    ____________________________________________________________");
        for (String input : start) {
            System.out.println(String.format("     %s",input));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void printEnd(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("     %s",input));
        System.out.println("    ____________________________________________________________");
        System.out.println();

    }


    public static void printNumberList(List<Task> lst) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("     %d.%s",i+1,lst.get(i)));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void doTask(String str) {
        int pos = Integer.parseInt(str)-1;
        Task task = lst.get(pos);
        task.doTask();
        List<String> inst = List.of("Nice! I've marked this task as done: ",
                "  "+task.toString());
        printInput(inst);
    }





   
}
