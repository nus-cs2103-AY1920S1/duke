import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    private static ArrayList<Task> tasks;
    private DukeException error;

    Duke() {
        error = new DukeException();
        tasks = new ArrayList<>();
    }

    void startDuke() throws FileNotFoundException, ParseException {
        importData();

        line();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        line();
    }

    void endDuke() {
        line();
        System.out.println("\tBye. Hope to see you again soon!");
        line();
    }

    private void importData() throws FileNotFoundException, ParseException {
        // pass the path to the file as a parameter
        File file =
                new File("C:\\Users\\Jasmine\\Desktop\\semester1\\CS2103T\\iP\\duke\\data\\startDuke.txt");
        Scanner s = new Scanner(file);

        Duke duke = new Duke();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" \\| ");

            Task taskToAdd = new Task();

            switch (task[0]) {
            case "D":
                taskToAdd = new Deadline(task[2], task[1].equals("+"), formatter.parse(task[3]));
                break;
            case "E":
                taskToAdd = new Event(task[2], task[1].equals("+"), formatter.parse(task[3]));
                break;
            case "T":
                taskToAdd = new Todo(task[2], task[1].equals("+"));
                break;
            default:
                duke.error(task[0]);
                break;
            }

            duke.add(taskToAdd);
        }
    }

    void saveData() throws IOException {
        FileWriter writer = new FileWriter("data\\duke.txt");
        for(Task task: tasks) {
            writer.write(task.toFile() + System.lineSeparator());
        }
        writer.close();
    }

    void listAll() {
        int counter = 1;

        //list out all the texts from the user
        line();
        System.out.println("\tHere are the tasks in your list:");
        for (Task t: tasks) {
            System.out.println("\t" + counter + "." + t.toString());
            counter++;
        }
        line();
    }

    void add(Task task) {
        //add task into the list of tasks
        tasks.add(task);
    }

    void markAsDone(int index) {
        //set task as done
        tasks.get(index - 1).done();
    }

    void delete(int index) {
        //retrieve task to delete
        tasks.remove(index);
    }

    void echo(Task task) {
        line();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        line();
    }

    void echoDone(int index) {
        Task task = tasks.get(index);
        //display the task
        line();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
        line();
    }

    void echoRemoved(int index) {
        Task task = tasks.get(index);
        line();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        line();
    }

    void error() {
        line();
        error.errorAction();
        line();
    }

    void error(String s) {
        line();
        error.incompleteAction(s);
        line();
    }

    private void line() {
        System.out.println("\t____________________________________________________________");
    }
}
