import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindCommand extends Command {

    public FindCommand(String action, String variable) {
        super(action, variable);

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        File f = new File(Storage.file);
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
