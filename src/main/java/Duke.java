import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasklist = new ArrayList<>();

    private static void draw_line() {
        System.out.print("    ");
        for (int i = 0; i < 70; i++) System.out.print("_");
        System.out.print("\n");
    }

    private static void add_task(String task_name) {
        Task next_task = new Task(task_name);
        tasklist.add(next_task);
        draw_line();
        System.out.println("     " + "added: " + task_name);
        draw_line();
    }

    private static void list_task() {
        draw_line();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            String indicator;
            if (tasklist.get(i).isFinished()) {indicator = ".[\u2713] ";}
            else {indicator = ".[\u2715] ";}
            System.out.println("     " + (i + 1) + indicator + tasklist.get(i).get_name());
        }
        draw_line();
    }

    private static void finish_task(int just_done) {
        draw_line();
        tasklist.get(just_done - 1).set_as_finish();
        System.out.println("     Nice! I have set this task as done:");
        System.out.println("       [\u2713] " + tasklist.get(just_done - 1).get_name());
        draw_line();
    }

    //This method will recognize the user input and call corresponding methods.
    //Except bye is recognized directly through the main method.
    private static void recognizer(String input) {
        String[] split_input = input.split(" ");
        if (split_input[0].equals("list")) list_task();
        else if (split_input[0].equals("done")) {
            int just_done = Integer.parseInt(split_input[1]);
            finish_task(just_done);
        } else {
            add_task(input);
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
            else recognizer(input);
        }

        //The exit page
        draw_line();
        System.out.println("     Bye. Hope to see you again soon!");
        draw_line();
    }
}
