public class ExitCommand extends Command {

  public String execute(TaskList tasks, Ui ui, Storage storage) {

    String output = "";

    // Save output as String
    output += ui.getTopBorder();
    output += "\n\tBye. Hope to see you again soon!";
    output += ui.getBottomBorder();
    return output;
  }

}