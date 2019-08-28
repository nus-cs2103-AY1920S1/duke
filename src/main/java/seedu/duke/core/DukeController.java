package seedu.duke.core;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class DukeController {

    /**
     * Handles the first level execution such as receiving the input string from Scanner and send the input to
     * Command.
     * @param ui UI of the system.
     * @param list Task List (ArrayList) where all tasks are stored.
     * @param storage storage unit which handles saving & updating the task list.
     * @param command command unit which handles parsing the input.
     * @param sc Scanner object
     * @throws IOException when file is corrupted or cannot be opened.
     */
    public void execute(Ui ui, List<Task> list, Storage storage, Command command,
                        Scanner sc) throws IOException {
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);

            String cmd = arr[0]; //command
            String description = "";
            ui.printLine();
            if (arr.length >= 2) {
                description = arr[1];
            }
            try {
                if (input.equals("bye")) {
                    exit = true;
                } else {
                    command.parseCommand(input, cmd, description, list, storage);
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (ParseException e) {
                System.out.println(e);
            }
        }
    }
}
