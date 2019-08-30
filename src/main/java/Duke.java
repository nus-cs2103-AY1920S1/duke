/* Name: Ang Kai Qi
   MatricNo: A0190206N
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String path) {
        this.ui = new UI();
        this.storage = new Storage(path);
        this.tasks = new TaskList(storage);
    }

    private void run() {
        ui.greet();
        String[] inputArr = storage.getInput().split("\\n");
        for (String s : inputArr) {
            String reply = tasks.addTask(s);
            if (reply.equals("bye")) {
                break;
            }
            ui.echo(reply);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt").run();
    }
}