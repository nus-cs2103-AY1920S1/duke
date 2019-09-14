import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListCommand extends Command {

    public ListCommand(String action) {
        super(action);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        ui.printIndent();
        if (TaskList.listOfTasks.isEmpty()) {
            System.out.println("There is no tasks in your list currently!!!");
        } else {
            System.out.println("Here are the tasks in your list:");
            File temp = new File(Storage.file);
            Scanner s = new Scanner(temp);
            int numbering = 1;
            while (s.hasNext()) {
                ui.printIndent();
                System.out.println(numbering + ". " + s.nextLine());
                numbering++;
            }
        }
    }
}
