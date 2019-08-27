import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class Duke {
    private DukeUI dukeUI;
    private StorageData storage;
    private TaskList tasks;

    public Duke(String filePath) {
        this.dukeUI = new DukeUI();
        this.storage = new StorageData(new File(filePath));
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File name has changed.");
        }
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        dukeUI.printWelcomeMessage();
        while(input.hasNextLine()) {
            try {
                Command c = Parser.parseCommand(input.nextLine());
                c.execute(this.tasks, this.dukeUI, this.storage);
            } catch (DukeWrongInputException e) {
                System.out.println(e.getMessage());
            } catch (DukeMissingDescriptionException ex) {
                System.out.println(ex.getMessage());
            } catch (DukeEmptyDescriptionException exx) {
                System.out.println(exx.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


