public class FindCommand extends Command {

  String description;

  public FindCommand(String description) {
    this.description = description;
  }

  public String execute(TaskList tasks, Ui ui, Storage storage) {

    String output = "";

    // Save output as String
    output += ui.getTopBorder();
    output += "\n\tHere are the matching tasks in your list: ";
    output += ui.getTasksAsString(tasks.findTasks(description));
    output += ui.getBottomBorder();
    return output;
  }
}