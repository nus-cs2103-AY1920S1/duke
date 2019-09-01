public class FindCommand extends Command {

  String description;

  public FindCommand(String description) {
    this.description = description;
  }

  public void execute(TaskList tasks, Ui ui, Storage storage) {

    // Printing output
    ui.showTopBorder();
    System.out.println("\n\tHere are the matching tasks in your list: ");
    ui.printTasks(tasks.findTasks(description));
    ui.showBottomBorder();
  }
}