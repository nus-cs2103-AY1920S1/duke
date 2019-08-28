import java.io.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        try {

            tasks = new TaskList(storage.load());

        } catch (DukeException e) {
            ui.showLoadingError();
           tasks = new TaskList();
        }
    }

    public void run() throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";
        System.out.println(line + "\n" + "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n" + line);

        ui.run(tasks, storage);
    }



    public static void main(String[] args) throws Exception {

        new Duke("DukeOutput.txt").run();

    }
}
