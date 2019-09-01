public class ListCommand extends Command {

  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showTopBorder();
    System.out.println("\n\tHere are the tasks in your list: ");
    ui.printTasks(tasks.getTaskList());
    ui.showBottomBorder();
  }

}