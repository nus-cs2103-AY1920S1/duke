import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String filePath = "data/duke.txt";
        File data = new File(filePath);
        System.out.println("\n" +
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        TaskTracker tracker = new TaskTracker(data);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            tracker.process(command);
            command = sc.nextLine();
        }
        String dataToOverwrite = tracker.end();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(dataToOverwrite);
            fw.close();
        } catch (Exception err) {
            System.err.println(err);
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
