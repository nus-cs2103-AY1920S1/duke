import Commands.Command;
import DirectProcessor.Parser;
import DirectProcessor.Storage;
import DirectProcessor.TaskList;
import DirectProcessor.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload());
        } catch(IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.takeCommand();
                ui.drawLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.drawLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("taskfile.txt").run();
    }
}



/**import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.*;
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
        }
        else if (task_type.equals("Event")) {
            toAdd = new Event(task_name, date_or_time);
        }
        else {
            toAdd = new Deadline(task_name, date_or_time);
        }
        tasklist.add(toAdd);

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
        tasklist.get(just_done - 1).set_as_finish();

        draw_line();
        System.out.println("     Nice! I have set this task as done:");
        System.out.println("       " + tasklist.get(just_done - 1).task_info());
        draw_line();
    }

    // This method removes task from the list
    private static void delete_task(int todelete) {
        Task.reduce_total_number();
        draw_line();
        System.out.println("     Noted, Noted. I've removed this task: ");
        System.out.println("       " + tasklist.get(todelete - 1).task_info());
        System.out.println("     Now you have " + Task.get_total_number() + " tasks in the list");
        draw_line();
        tasklist.remove(todelete - 1);
    }

    //This method will recognize the user input and call corresponding methods.
    //Except bye is recognized directly through the main method.
    private static void recognizer(String input) throws DukeException {
        String[] split_input = input.split(" ");

        // When the user want to see the task list.
        if (split_input[0].equals("list")) list_task();

        // When the user has finished a task.
        else if (split_input[0].equals("done")) {
            int just_done = Integer.parseInt(split_input[1]);
            if (just_done > tasklist.size()) {
                throw new TaskNumberTooBigException("The task index is too big.");
            }
            finish_task(just_done);
        }

        // When the user want to add a todo task.
        else if (split_input[0].equals("todo")) {
            String task_name = "";
            for (int i = 1; i < split_input.length; i++) {
                task_name = task_name + split_input[i];
                if (i != split_input.length - 1) task_name = task_name + " ";
            }
            if (task_name.equals("")) {
                throw new TodoNotSpecifiedException("The todo has an empty description");
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
            if (time_start_from != 0) {
                for (int i = time_start_from; i < split_input.length; i++) {
                    task_time = task_time + split_input[i];
                    if (i != split_input.length - 1) task_time = task_time + " ";
                }
            }
            if (task_name.equals("")) {
                throw new EventNameException("The event has no description");
            }
            if (task_time.equals("")) {
                throw new EventTimeException("The event has no time");
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
            if (time_start_from != 0) {
                for (int i = time_start_from; i < split_input.length; i++) {
                    task_time = task_time + split_input[i];
                    if (i != split_input.length - 1) task_time = task_time + " ";
                }
            }
            if (task_name.equals("")) {
                throw new DeadlineNameException("Deadline has no description.");
            }
            if (task_time.equals("")) {
                throw new DeadlineTimeException("Deadline has no deadline.");
            }
            add_task("Deadline", task_name, task_time);
        }

        else if (split_input[0].equals("delete")) {
            int todelete = Integer.parseInt(split_input[1]);
            if (todelete > tasklist.size()) {
                throw new TaskNumberTooBigException("The number is too big");
            }
            delete_task(todelete);
        }

        // When input is invalid
        else {
            throw new InvalidKeywordException("The keyword cannot be understood by the recognizer.");
        }
    }

    //The main method will be used to send the user's input to the recognizer.
    //Except it should recognize the bye input by itself.
    //It should also catch the exceptions thrown by the recognizer.
    public static void main(String[] args) {
        // Check if the dukedata directory exists or not.
        // If it does not, it means duke is the first time to run by the user.
        // Create the dukedata directory.
        if (!(new File("./dukedata").exists())) {
            new File("./dukedata").mkdir();
        }

        // Reload the previous task list.
        try {
            tasklist = FileManager.reload();
        } catch (IOException e) {
            System.out.println("Previous task list cannot be loaded.");
        }

        // The welcome page.
        draw_line();
        System.out.println("     Hello, I'm Duke.");
        System.out.println("     What can I do for you?");
        draw_line();

        // From now on, start getting user's input.
        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // Find if the user's input is bye.
            if (input.equals("bye")) break;

            // Since the user's input is not bye, The user's input will now be thrown to the recognizer.
            // main method will also inspect what kind of exception is thrown by recognizer.
            // Based on what kind of exception is thrown, the main method will decide what is wrong.
            else {
                try{
                    recognizer(input);
                } catch (InvalidKeywordException e) {
                    draw_line();
                    System.out.println("     OOPS!!! I'm sorry, but I don't know what that means :-(");
                    draw_line();
                } catch (TodoNotSpecifiedException e) {
                    draw_line();
                    System.out.println("     OOPS!!! The description of a todo cannot be empty.");
                    draw_line();
                } catch (EventNameException e) {
                    draw_line();
                    System.out.println("     OOPS!!! The description of an event cannot be empty.");
                    draw_line();
                } catch (EventTimeException e) {
                    draw_line();
                    System.out.println("     OOPS!!! The event must have a specific time");
                    draw_line();
                } catch (DeadlineNameException e) {
                    draw_line();
                    System.out.println("     OOPS!!! The description of a deadline cannot be empty.");
                    draw_line();
                } catch (DeadlineTimeException e) {
                    draw_line();
                    System.out.println("     OOPS!!! The deadline must have a specific deadline.");
                    draw_line();
                } catch(TaskNumberTooBigException e) {
                    draw_line();
                    System.out.println("     OOPS!!! You are doing things with a task that does not exist.");
                    draw_line();
                } catch (DukeException e) {
                    draw_line();
                    System.out.println("     OOPS!!! An unexpected error just happened.");
                    draw_line();
                }
            }
        }

        // Rewrite the task list file.
        try {
            FileManager.rewrite(tasklist);
        } catch (IOException e) {
            System.out.println("Oops, modification this time cannot be recorded.");
        }

        //The exit page.
        draw_line();
        System.out.println("     Bye. Hope to see you again soon!");
        draw_line();
    }
}*/
