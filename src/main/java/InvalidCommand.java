public class InvalidCommand extends Command {

  String errorMessage;

  public InvalidCommand(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void execute(TaskList tasks, Ui ui, Storage storage) {

    // Printing Output
    ui.showTopBorder();
    System.out.println("\n\t" + errorMessage);
    ui.showBottomBorder();

  }
}