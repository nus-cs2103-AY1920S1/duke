package duke.execution.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.Ui;

public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     *
     * @param action List Command word.
     */
    public ListCommand(String action) {
        super(action);
    }

    /**
     *
     *
     * @param errands Not needed in this case.
     * @param ui Not needed in this case.
     * @param storage Not needed in this case.
     * @return Returns those tasks that match the keyword.
     * @throws FileNotFoundException If there is no prior list of task
     *                               available, there is nothing to print,
     *                               thus, an error message will be shown.
     */
    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws FileNotFoundException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        if (CompleteList.listOfPlans.isEmpty()) {
            return "There is no tasks in your list currently!!!";
        } else {
            String list = "Here are the tasks in your list:\n";
            File temp = new File(Storage.file);
            Scanner s = new Scanner(temp);
            int numbering = 1;
            while (s.hasNext()) {
                list += numbering + ". " + s.nextLine() + "\n";
                numbering++;
            }
            return list;
        }
    }
}
