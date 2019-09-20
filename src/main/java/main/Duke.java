package main;

import command.Command;
import command.ExitCommand;
import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main driver of the program.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList task;

    /**
     * Creates the Ui, Storage and Tasklist objects.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage("./todoList.txt");
            this.task = new TaskList(storage.fileInitialization());
        } catch (FileNotFoundException e) {
            System.out.println(ui.printError("File not found"));
        } catch (IOException e) {
            System.out.println(ui.printError(e.getMessage()));
        } catch (DukeException e) {
            System.out.println(ui.printError(e.getMessage()));
        }

    }

    /**
     * Creates and run duke.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.printMessage("Hello! I'm Duke\nWhat can i do for you?"));

        try {
            Duke duke = new Duke();
            duke.run();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }

    /**
     * Starts the program sequence.
     */
    public void run() throws IOException {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] temp = input.split(" ");
            while (temp.length == 0) {
                System.out.println(ui.printMessage("Please input something :("));
                input = sc.nextLine();
                temp = input.split(" ");
            }
            Command c = Parser.parse(input);
            System.out.println(c.execute(task, ui, storage));
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println(new ExitCommand().execute(task, ui, storage));
        }
    }

    String getResponse(String input) {
        String output;

        if (input.equals("bye")) {
            Platform.exit();
        }

        try {
            assert !input.equals("bye") : "input = bye, error in code.";

            Command c = Parser.parse(input);
            output = c.execute(task, ui, storage);
        } catch (IOException e) {
            output = e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            output = ui.printError("Please input something :(");
        }

        return output;
    }
}

