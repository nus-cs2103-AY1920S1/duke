import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static List<Task> lst = new ArrayList<>();
    final private static String horizontalLine = "    ____________________________________________________________";

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
                try {
                    doTask(tokens[1]);
                } catch (NumberFormatException error) {
                    printInput(List.of("☹ Invalid input must be an integer"));
                } catch (IllegalArgumentException error2) {
                    printInput(List.of(error2.getMessage()));
                } catch (IndexOutOfBoundsException error3) {
                    printInput(List.of("☹ No such task"));
                }
            } else if (tokens[0].equals("delete")) {
                try {
                    deleteTask(tokens[1]);
                } catch (NumberFormatException error) {
                    printInput(List.of("☹ Invalid input must be an integer"));
                } catch (IllegalArgumentException error2) {
                    printInput(List.of(error2.getMessage()));
                } catch (IndexOutOfBoundsException error3) {
                    printInput(List.of("☹ No such task"));
                }
            } else {
                createTask(tokens);
            }

        }

    }

    public static void printInput(Task input) {
        System.out.println(horizontalLine);

        System.out.println("     Got it. I've added this task:");
        System.out.println(String.format("       %s",input));
        System.out.println(String.format("     Now you have %d tasks in the list.",lst.size()));
        System.out.println(horizontalLine);
        System.out.println();

    }

    public static void printInput(List<String> start) {
        System.out.println(horizontalLine);
        for (String input : start) {
            System.out.println(String.format("     %s",input));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    public static void printEnd(String input) {
        System.out.println(horizontalLine);
        System.out.println(String.format("     %s",input));
        System.out.println(horizontalLine);
        System.out.println();

    }


    public static void printNumberList(List<Task> lst) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("     %d.%s",i+1,lst.get(i)));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    public static void doTask(String str) throws NumberFormatException, IllegalArgumentException, IndexOutOfBoundsException {
        int pos = Integer.parseInt(str)-1;
        Task task = lst.get(pos);
        boolean isDoneBefore = task.doTask();
        if (isDoneBefore) {
            throw new IllegalArgumentException("Task has already been done");
        }
        List<String> inst = List.of("Nice! I've marked this task as done: ",
                "  "+task.toString());
        printInput(inst);
    }

    public static void createTask(String [] tokens) {
        Task task = null;
        if (tokens[0].equals("todo")) {
            try {
                checkValidLength(tokens);
                task = ToDo.createToDo(tokens);
            } catch (IllegalArgumentException error2){
                printInput(List.of(error2.getMessage()));
            }
        } else if (tokens[0].equals("deadline")) {
            try {
                checkValidLength(tokens);
                if (Arrays.asList(tokens).contains("/by")) {
                    throw new IllegalArgumentException("Missing deadline");
                } else {
                    task = Deadline.createDeadline(tokens);
                }
            } catch (IllegalArgumentException error2){
                printInput(List.of(error2.getMessage()));
            }
        } else if (tokens[0].equals("event")) {
            try {
                checkValidLength(tokens);
                if (Arrays.asList(tokens).contains("/at")) {
                    throw new IllegalArgumentException("Missing deadline");
                } else {
                    task = Event.createEvent(tokens);
                }
            } catch (IllegalArgumentException error2){
                printInput(List.of(error2.getMessage()));
            }
        } else {
            printInput(List.of("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }

        if (task != null) {
            lst.add(task); 
            printInput(task);
        }
    }



    public static void checkValidLength(String[] tokens) throws IllegalArgumentException {
        if (tokens.length == 1) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! The description of a todo cannot be empty.",tokens[0]));
        }
    }   

   
}
