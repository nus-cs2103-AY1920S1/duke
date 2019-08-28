import java.io.File;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
            tasks = new TaskList(storage.load());

    }
    public static void main(String[] args) throws DukeException{
        new Duke("save.txt").run();
    }

    public void Greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void run() {
        Greet();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);
        parser.read(tasks);
        storage.save(tasks);
    }
}

