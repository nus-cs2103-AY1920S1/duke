import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Listener listener = new Listener();
        try {
            Sheet sheet = new Sheet(readTasks());
            listener.start(sheet);
        } catch (IOException e) {
            System.out.println(Formatter.INDENT + "oops, something went wrong");
        }
    }

    public static File readTasks() throws IOException {
         File f = new File(myPaths.TASKLIST);
         f.createNewFile();
         return f;
    }
}
