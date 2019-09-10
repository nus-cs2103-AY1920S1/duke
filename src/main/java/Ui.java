import java.io.FileNotFoundException;

/**
 * Shows the interface
 */
public class Ui {

    public void run(TaskList tasks, Storage storage) throws FileNotFoundException {
        final String DUKE_LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String LINE_BORDER = "____________________________________________________________";

        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(LINE_BORDER + "\n" + "Hello! I'm Duke" + "\n" +
                "What can I do for you?" + "\n" + LINE_BORDER);

        Parser parser = new Parser(storage, tasks);
        parser.run();
    }

    /**
     * prints error message if file is not available
     */
    public void showLoadingError(){
        System.out.println("File not available");
    }

}
