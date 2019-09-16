package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

public class DoneCommand implements Command {
    private String arguments;

    /**
     * Creates an instance of DoneCommand with its arguments
     *
     * @param arguments arguments of the Command
     */
    public DoneCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Parses the arguments of the Command and executes it
     *
     * @param tasks   the TaskList of Tasks
     * @param ui      The User Interface
     * @param storage Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "";
        try{
            int index = Integer.parseInt(arguments.trim());
            tasks.get(index - 1).markAsDone();

            content = content.concat("Nice! I've marked this task as done:\n" +
                    "[" + tasks.get(index - 1).getSymbol() + "]" +
                    "[" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription());

            if (tasks.get(index - 1).getSymbol() == 'D') {
                if(tasks.get(index - 1).getDetails() != null){
                    content = content.concat(" (by: " + tasks.get(index - 1).getTime() + ")");
                }
            } else if (tasks.get(index - 1).getSymbol() == 'E') {
                if(tasks.get(index - 1).getDetails() != null) {
                    content = content.concat(" (at: " + tasks.get(index - 1).getDetails() + ")");
                }
            }
            content = content.concat("\n");
        } catch (Exception E){
            content = "Ohno! You have entered an invalid argument!\n" +
                    "Usage: done <id>\n";
        }


        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
