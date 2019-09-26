package duke.execution.command;

import duke.exceptions.DukeException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.Ui;

public class FindCommand extends Command {

    /**
     * Constructor for FindCommand.
     *
     * @param action Find command word.s
     * @param variable Number of task to be deleted.
     */
    public FindCommand(String action, String variable) {
        super(action, variable);

    }

    /**
     * Executes the deadline command and prints out statements to
     * tell the user that the deadline tasks has been added to
     * the list of tasks.
     *
     * @param errands Not needed in this case.
     * @param ui Prints out statements to indicate to user what
     *           has happened.
     * @param storage Not needed in this case.
     * @return Returns String to print out to the user.
     * @throws IOException If the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     * @throws DukeException If there is no matching word, an error message
     *                       will be sent to the user.
     */
    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws DukeException, IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        File f = new File(Storage.file);
        assert f != null;
        Scanner sc = new Scanner(f);
        ArrayList<String> tempList = new ArrayList<>();
            int num = 1;
            while (sc.hasNext()) {
                String text = sc.nextLine();
                int spaceIndex = text.indexOf(" ");
                int bracketIndex = text.length();
                if (text.contains("(")) {
                    bracketIndex = text.indexOf("(");
                }
                String description = text.substring(spaceIndex + 1, bracketIndex);
                if (description.contains(variable)) {
                    String task = num + "." + text;
                    tempList.add(task);
                    num++;
                }
            }
            if (tempList.isEmpty()) {
                Ui.printIndent();
                throw new DukeException("No such word is found in any of the tasks.");
            } else {
                Ui.printIndent();
                String matchingTask = "Here are the matching tasks in your list!\n";
                for (String str : tempList) {
                    Ui.printIndent();
                    matchingTask += str + "\n";
                }
                return matchingTask;
            }
    }
}
