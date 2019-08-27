package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Command;
import seedu.duke.model.Storage;
import seedu.duke.model.Task;
import seedu.duke.model.Ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class DukeController {

    public void execute(Ui ui, List<Task> list, Storage storage, Command command,
                        Scanner sc) throws IOException{
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);

            String cmd = arr[0]; //command
            String description = "";
            ui.printLine();
            if (arr.length >= 2)
                description = arr[1];
            try {
                if (input.equals("bye")) {
                    exit = true;
                } else {
                    command.parseCommand(input, cmd, description, list, storage);
                }
            } catch(DukeException e) {
                System.out.println(e);
            } catch(ParseException e) {
                System.out.println(e);
            }
            ui.printLine();
        }
    }

    public static void main(String[] args) {

    }
}
