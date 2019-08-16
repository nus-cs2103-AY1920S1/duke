public class Command {
    protected final String keyword;
    protected final String args;
    protected final String slashKeyword;
    protected final String slashArgs;

    /**
     * Creates a command with a keyword and its arguments both before and after the slash, given an input string.
     * @param input Command input as a string
     */
    public Command(String input) {
        String inputBeforeSlash = input.split("/", 2)[0];
        String inputAfterSlash = input.split("/").length >= 2 ? input.split("/", 2)[1] : null;

        this.keyword = inputBeforeSlash.split(" ", 2)[0];
        this.args = inputBeforeSlash.split(" ").length >= 2 ? inputBeforeSlash.split(" ", 2)[1] : null;

        if (inputAfterSlash == null) {
            this.slashKeyword = null;
            this.slashArgs = null;
        } else {
            this.slashKeyword = inputAfterSlash.split(" ", 2)[0];
            this.slashArgs = inputAfterSlash.split(" ").length >= 2 ? inputAfterSlash.split(" ", 2)[1] : null;
        }
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getArgs() {
        return this.args;
    }

    public String getSlashKeyword() {
        return this.slashKeyword;
    }

    public String getSlashArgs() {
        return this.slashArgs;
    }
}
