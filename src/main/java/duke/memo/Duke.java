package duke.memo;

import duke.memo.command.Command;
import duke.memo.data.RecordList;
import duke.memo.exception.DukeException;
import duke.memo.parser.InputParser;
import duke.memo.storage.Storage;
import duke.memo.message.MessageGenerator;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private RecordList recordList;
    private MessageGenerator msgGenerator;
    public static final String filePath = "data/duke.txt";

    /**
     * Constructor for Duke.
     * Throw error if input/output goes wrong.
     */
    public Duke() {
        try {
            msgGenerator = new MessageGenerator();
            storage = new Storage(filePath);
            recordList = new RecordList(storage.load());
        } catch (DukeException e) {
            msgGenerator.generateErrorMsg(e.getMessage());
            recordList = new RecordList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get response for input.
     * @param input input from GUI.
     * @return return the result as a string
     */
    public String getResponse(String input)  {
        Command c;
        try {
            c = InputParser.parse(input);
            return c.execute(recordList, msgGenerator, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
