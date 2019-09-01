public class ExitCommand extends Command {

  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showTopBorder();
    System.out.println("\n\tBye. Hope to see you again soon!");
    ui.showBottomBorder();
  }

}