import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();  //if the file is corrupted, the tasks list will start fresh
        }
    }

    public void run() {
        ui.introduction();
        while(true) {
            try {
                String commandLine = ui.getNextLine();
                Parser p = new Parser(commandLine);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();


        while(reader.hasNextLine()) {
            String input = reader.next();
            if(input.equals("bye")) {
                System.out.println(horLine);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(horLine);
                break;
            } else if(input.equals("list")){
                System.out.println(horLine);
                if(taskList.isEmpty()) {
                    System.out.println("Sorry, there are no tasks in the list");
                } else {
                    System.out.println("     Here are the tasks in your list:");
                    int counter = 1;
                    for (Task task : taskList) {
                        System.out.println("     " + counter + "." + task);
                        counter++;
                    }
                }
                System.out.println(horLine + "\n");

            } else if(input.equals("done")) {
                int number = reader.nextInt();
                Task temp = taskList.get(number - 1);
                temp.setDone();
                System.out.println(horLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + temp);
                System.out.println(horLine + "\n");
                storage.arrayDataToFile(taskList);
            } else if(input.equals("delete")) {
                int number = reader.nextInt();
                tasks.deleteTask(number);
                System.out.println(horLine);
                System.out.println("     Nice! I've removed this task:");
                System.out.println("       " + temp);
                System.out.println("      Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(horLine + "\n");
                storage.arrayDataToFile(taskList);
            } else{  //all other commands
                try {
                    String taskDetails = reader.nextLine().trim();
                    tasks.addTask(input, taskDetails);
                } catch (DukeException de) {
                    System.out.println(horLine);
                    System.err.println(de.getMessage());
                    System.out.println(horLine + "\n");
                    continue;  //to prevent printing of below mentioned lines
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println(horLine);
                    System.err.println("      ☹ OOPS!!! You need to specify the " + input +
                            " time through a /by (deadline) and /at (event)");
                    System.out.println(horLine + "\n");
                    continue;
                }
                System.out.println(horLine);
                System.out.println("      Got it. I've added this task:");
                System.out.println("       " + taskList.get(taskList.size() - 1));
                System.out.println("      Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(horLine);
                System.out.println();
                storage.arrayDataToFile(taskList);
            }
        }
    }
}