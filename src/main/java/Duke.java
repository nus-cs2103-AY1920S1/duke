import java.util.*;
import java.io.*;

public class Duke {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] token;
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String underline = "____________________________________________________________\n";

    private void run() throws IOException {
        System.out.println(underline + "Hello from\n" + logo + "\nWhat can i do for you?\n" + underline);

        token = br.readLine().split(" ");
        while(!(token[0].equals("bye"))) {
            System.out.println(underline + token[0] + "\n" + underline);
            token = br.readLine().split(" ");
        }
        System.out.print(underline + "Bye. Hope to see you again soon!\n" + underline);
    }




    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

