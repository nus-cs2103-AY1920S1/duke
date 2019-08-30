public class ListCommand extends Command {


    public void execute(TaskList taskList, Ui ui, Storage storage) {

        String result = "Here are the tasks in your list:\n";
        int index = 1;
        for (int i = 1; i <= taskList.size(); i++) {
            result += "\n" + index + ". " + taskList.getTask(i);
            index++;
        }


        ui.printText(result);


    }

    public boolean isExit() {
        return false;
    }
}