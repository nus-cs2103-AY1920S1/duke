package duke.main;

<<<<<<< HEAD
import duke.exception.DukeException;
import duke.exception.EmptyDeadlineDscDukeException;
import duke.exception.EmptyTodoDscDukeException;
import duke.exception.UnknownCmdDukeException;
import duke.exception.EmptyEventDscDukeException;
import duke.exception.NoDateDukeException;
import duke.exception.InvalidTaskIndexDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import java.io.FileNotFoundException;
import java.text.ParseException;
=======
import duke.command.Command;
import duke.exception.SaveFileWrongFormatDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.FileNotFoundException;
>>>>>>> code-quality-fixing

/**
 * Duke is an application that keeps track of your
 * to-do tasks, deadlines and events.
 */
public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for duke.
     */
    public Duke() {
        storage = new Storage("data/savedList.txt");
        parser = new Parser();
        try {
            tasks = storage.load(parser);
        } catch (FileNotFoundException | SaveFileWrongFormatDukeException e) {
            tasks = new TaskList();
            System.out.println(e.getMessage());
        }
    }

    /**
     * This is where the Duke application starts to run.
     */
    public String getResponse(String input) {
        Command commandFromInput = parser.parse(input);
        String returnString = commandFromInput.execute(tasks);
        storage.saveDuke(tasks.saveInfo());
        return returnString;
    }
}
