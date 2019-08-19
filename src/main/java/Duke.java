import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasklist = new ArrayList<>();

    private static void draw_line() {
        System.out.print("    ");
        for (int i = 0; i < 70; i++) System.out.print("_");
        System.out.print("\n");
    }

    // This method add a new task into the task list and print out add message.
    private static void add_task(String task_type, String task_name, String date_or_time) {
        // Add the right type of task
        Task toAdd;
        if (task_type.equals("Todo")) {
            toAdd = new Todo(task_name);
            tasklist.add(toAdd);
        }
        else if (task_type.equals("Event")) {
            toAdd = new Event(task_name, date_or_time);
            tasklist.add(toAdd);
        }
        else {
            toAdd = new Deadline(task_name, date_or_time);
            tasklist.add(toAdd);
        }

        // Print out the message
        draw_line();
        System.out.println("     Got it. I have added this task:");
        System.out.println("       " + toAdd.task_info());
        System.out.println("     You have now " + Task.get_total_number() + " tasks in the list.");
        draw_line();
    }

    // This method list down all tasks
    private static void list_task() {
        draw_line();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println("       " + (i + 1) + "." + tasklist.get(i).task_info());
        }
        draw_line();
    }

    // This method sets task as finished
    private static void finish_task(int just_done) {
        draw_line();
        tasklist.get(just_done - 1).set_as_finish();
        System.out.println("     Nice! I have set this task as done:");
        System.out.println("       " + tasklist.get(just_done - 1).task_info());
        draw_line();
    }

    //This method will recognize the user input and call corresponding methods.
    //Except bye is recognized directly through the main method.
    private static void recognizer(String input)  {
        String[] split_input = input.split(" ");

        // When the user want to see the task list.
        if (split_input[0].equals("list")) list_task();

        // When the user has finished a task.
        else if (split_input[0].equals("done")) {
            int just_done = Integer.parseInt(split_input[1]);
            finish_task(just_done);
        }

        // When the user want to add a todo task.
        else if (split_input[0].equals("todo")) {
            String task_name = "";
            for (int i = 1; i < split_input.length; i++) {
                task_name = task_name + split_input[i];
                if (i != split_input.length - 1) task_name = task_name + " ";
            }
            add_task("Todo", task_name, null);
        }

        // When the user want to add an event task.
        else if (split_input[0].equals("event")) {
            String task_name = "";
            String task_time = "";
            int time_start_from = 0;
            for (int i = 1; i < split_input.length; i++) {
                if (split_input[i].equals("/at")) {
                    time_start_from = i + 1;
                    break;
                }
                if (i != 1) task_name = task_name + " ";
                task_name = task_name + split_input[i];
            }
            for (int i = time_start_from; i < split_input.length; i++) {
                task_time = task_time + split_input[i];
                if (i != split_input.length - 1) task_time = task_time + " ";
            }
            add_task("Event", task_name, task_time);
        }

        // When the user want to add a deadline task.
        else if (split_input[0].equals("deadline")) {
            String task_name = "";
            String task_time = "";
            int time_start_from = 0;
            for (int i = 1; i < split_input.length; i++) {
                if (split_input[i].equals("/by")) {
                    time_start_from = i + 1;
                    break;
                }
                if (i != 1) task_name = task_name + " ";
                task_name = task_name + split_input[i];
            }
            for (int i = time_start_from; i < split_input.length; i++) {
                task_time = task_time + split_input[i];
                if (i != split_input.length - 1) task_time = task_time + " ";
            }
            add_task("Deadline", task_name, task_time);
        }

        // When input is invalid
        else {
            draw_line();
            System.out.println("I do not know what you are talking about");
            draw_line();
        }
    }

    //The main method will be used to recognize inputs and call relative methods
    public static void main(String[] args) {
        // The welcome page.
        draw_line();
        System.out.println("     Hello, I'm Duke.");
        System.out.println("     What can I do for you?");
        draw_line();

        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) break;

            // The pot will be thrown to the recognizer
            else recognizer(input);
        }

        //The exit page
        draw_line();
        System.out.println("     Bye. Hope to see you again soon!");
        draw_line();
    }
}
