import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListCommand extends Command {

    public ListCommand(String action) {
        super(action);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        ui.printIndent();
        if (TaskList.listOfTasks.isEmpty()) {
            return "There is no tasks in your list currently!!!";
        } else {
            String list = "Here are the tasks in your list:\n";
            File temp = new File(Storage.file);
            Scanner s = new Scanner(temp);
            int numbering = 1;
            while (s.hasNext()) {
                ui.printIndent();
                list += numbering + ". " + s.nextLine() + "\n";
                numbering++;
            }
            return list;
        }
    }
}
