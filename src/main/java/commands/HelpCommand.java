package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.*;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    /**
     * This method is used to generate the help page for new users.
     *
     * @param tasks   the TaskList object to be used in this command
     * @param ui      the Ui object to be used in this command
     * @param storage the Storage object to be used in this command
     * @return duke opens the help file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        InputStream in = getClass().getResourceAsStream("help.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String output = "";
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                output += (line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
