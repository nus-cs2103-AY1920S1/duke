package seedu.duke.core;

import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DukeController {

    /**
     * Handles the first level execution such as receiving the input string from Scanner and send the input to
     * Command.
     * @param ui UI of the system.
     * @param list task List (ArrayList) where all tasks are stored.
     * @param storage handles saving and updating the task list.
     * @param parser handles parsing the input.
     * @throws IOException when file is corrupted or cannot be opened.
     */
    public String executeFx(Ui ui, List<Task> list, Storage storage, Parser parser,
                          String input) throws IOException {
        String output = "";
        String[] parsed_inputs = input.split(" ", 2);

        String cmd = parsed_inputs[0]; //command
        String description = "";

        if (parsed_inputs.length >= 2) {
            description = parsed_inputs[1];
        }
        try {
            output += parser.parseCommand(input, cmd, description, list, storage, ui).getMsg();
        } catch (DukeException e) {
            output += e.toString();
        } catch (ParseException e) {
            output += e.toString();
        }

        return output;
    }
}
