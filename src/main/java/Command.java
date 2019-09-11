import java.io.IOException;
import java.text.ParseException;

/**
 * Contains information given by user about command.
 * Represents a command. A <code>Command</code>
 * corresponds to a main command word (e.g. <code>list</code>, <code>deadline</code> etc.)
 * and details required for it to be execute (e.g. description of event, index of task to be
 * deleted from list, details of subcommands like <code>/by</code>).
 */
public class Command {

    protected String command; // e.g. list, done, bye, todo, deadline, event
    protected boolean toContinueProgram; // Whether command causes program to exit or not

    public Command(String commandWord) {
        this.command = commandWord;
        this.toContinueProgram = !commandWord.equals("bye");
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
    }

    public boolean toContinue() {
        return toContinueProgram;
    }

    /**
     * To help with debugging.
     */
    public void print() {
        System.out.println("Command: " + command);
        System.out.print("Does command terminate program?: " + !toContinueProgram);
    }

    /**
     * For testing and assertions.
     * @param o Object to compare to.
     * @return Whether object is equivalent/equal.
     */
    @Override
    public boolean equals(Object o) {
        // Not even same class
        if (!(o instanceof Command)) {
            return false;
        }
        // Same object
        if (o == this) {
            return true;
        }
        Command c = (Command) o;
        return command.equals(c.command) &&
                toContinueProgram == c.toContinueProgram;
    }

}
