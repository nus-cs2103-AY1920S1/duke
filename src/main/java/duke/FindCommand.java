package duke;

public class FindCommand extends Command {
    private String searchParams;

    public FindCommand(String searchParams) {
        super(CommandType.FIND);
        this.searchParams = searchParams;
    }

    public String getSearchParams() {
        return searchParams;
    }
}
