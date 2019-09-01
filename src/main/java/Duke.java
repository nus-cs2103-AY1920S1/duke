import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Parser parser;
    private Storage storage;
    private String filePath;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.filePath = filepath;
    }

    public void run() {
        this.ui.printWelcomeMessage();
        try {
            this.storage = new Storage(filePath);
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.ui.messageUser(e.getMessage());
        }catch (FileNotFoundException e) {
            this.ui.messageUser("The file is missing :(");
        } catch (IOException e) {
            this.ui.messageUser("Trouble creating file");
        }
        boolean isExit = false;
        while(!isExit) {
            try {
                Scanner scanner = new Scanner(System.in);
                String userMessage = this.ui.readMessage(scanner);
                Command command = Parser.parseUserMessage(userMessage);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.messageUser(e.getMessage());
            }
        }
        try {
            this.storage.save(this.tasks);
        } catch (IOException e) {
            this.ui.messageUser("ERROR WRITING TO FILE :(");
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
