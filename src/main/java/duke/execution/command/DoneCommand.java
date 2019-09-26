package duke.execution.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.Ui;
import duke.execution.TaskList;

public class DoneCommand extends Command {

    /**
     * Constructor for Done command.
     *
     * @param action Description of the task.
     * @param variable Number of tasks that is completed.
     */
    public DoneCommand(String action, String variable) {
        super(action, variable);
    }

    /**
     * Executes the done command and prints out statements to
     * tell the user that the deadline tasks has been added to
     * the list of tasks.
     *
     * @param errands Not needed in this case.
     * @param ui Not needed in this case.
     * @param storage Not needed in this case.
     * @return Returns String to print out to the user.
     */
    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        int taskNum = Integer.parseInt(variable);
        System.out.println(taskNum - getNumOfExpensesBeforeDoneTask(taskNum) - 1);
        TaskList.listOfTasks.get(taskNum - getNumOfExpensesBeforeDoneTask(taskNum) - 1).markAsDone();
        String doneOutput = "Nice! I've marked this task as done:\n";
        doneOutput += TaskList.listOfTasks.get(taskNum - 1).toString();
        return doneOutput;
    }

    /**
     *
     *
     * @param taskNum
     * @return
     * @throws IOException
     */
    public static int getNumOfExpensesBeforeDoneTask(int taskNum) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Storage.file));
            String text;
            int counter = 0;
            int lineCounter = 0;
            while ((text = br.readLine()) != null) {
                lineCounter++;
                if (text.contains("Expenses")) {
                    counter++;
                }
                if (lineCounter == taskNum) {
                    break;
                }
            }
            return counter;
        } catch (IOException e) {
            System.out.println(e);
        }
        return 0;
    }
}
