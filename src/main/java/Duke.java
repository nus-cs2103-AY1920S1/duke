import storage.Storage;
import tasklist.TaskList;
import parser.Parser;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Scanner;

public class Duke {
    private Storage saveFile;

    public Duke(String filepath) throws IOException {
        saveFile = new Storage(filepath);
    }

    public void run() throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String user = input.nextLine();
        TaskList scheduler;
        Parser parser = new Parser();

        while (!user.equals("bye")) {
            scheduler = new TaskList(saveFile.loadData());
            parser.parse(user, scheduler);
            if (!parser.isSafe()) {
                continue;
            }
            saveFile.storeData(scheduler.getTaskList());
            user = input.nextLine();
        }


        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }


    public static void main(String[] args) throws IOException {
        new Duke("tasklist.txt").run();
    }
}
