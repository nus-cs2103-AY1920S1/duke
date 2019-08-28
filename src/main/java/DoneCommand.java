import java.io.IOException;

public class DoneCommand extends Command {

    DoneCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            //Error if user inputs spaces
            if (commandDesc.substring(5).split(" ")[0].equals("")) {
                throw new InvalidTaskNumberDukeException("empty");
            }
            int taskNumber = Integer.parseInt(commandDesc.substring(5).split(" ")[0]);
            //Check if task number is valid
            if (taskNumber > 0 && taskNumber <= tasks.taskList.size()) {
                storage.editsFile(System.getProperty("user.dir"), tasks.taskList.get(taskNumber - 1).stringForAppend());
                tasks.taskList.get(taskNumber - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.taskList.get(taskNumber - 1));
            } else {
                throw new InvalidTaskNumberDukeException("invalid");
            }
        } catch (IndexOutOfBoundsException err) {
            //If user input for task number is empty
            throw new InvalidTaskNumberDukeException("empty");
            //If non-numeric input given for task number
        } catch (NumberFormatException err) {
            throw new InvalidTaskNumberDukeException("invalid");
        }
    }
}
