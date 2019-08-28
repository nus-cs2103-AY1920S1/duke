public class ListCommand extends Command {
    protected TaskList taskList;

    public ListCommand() {
        super.carryOn = true;
    }

    @Override
    public void execute(TaskList taskList) {
        this.taskList = taskList;
        System.out.println(TextUi.DIVIDER + TextUi.NEWLINE + "\tHere are the tasks in your list:");

        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.get(i));
        }

        System.out.println(TextUi.DIVIDER);
    }
}