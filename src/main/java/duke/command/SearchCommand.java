package duke.command;

public class SearchCommand extends Command {
    public SearchCommand(String keyword) {
        super(Type.SEARCH, keyword);
    }
}