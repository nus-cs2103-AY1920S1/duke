package main;

import command.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main driver of the program.
 */
public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.printMessage("Hello! I'm Duke\n     What can i do for you?");

        try {
            run();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }

    /**
     * Starts the program sequence.
     */
    public static void run() throws IOException {

        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("./todoList.txt");
        TaskList task = new TaskList(storage.fileInitialization());

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] temp = input.split(" ");
            while (temp.length == 0) {
                ui.printMessage("Please input something :(");
                input = sc.nextLine();
                temp = input.split(" ");
            }
            Command c = Parser.parse(input);
            c.execute(task, ui, storage);
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            new ExitCommand().execute(task, ui, storage);
        }
    }
}
