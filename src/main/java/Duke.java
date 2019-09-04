import java.io.IOException;


public class Duke {

	private static UI ui;
	private Storage storage;
	private Tasklist tasklist;

    public static void main(String[] args) throws IOException {
		new Duke();
    }

	/**
	 * Constructor for Duke
	 * Creates new Storage instance and Parser
	 */
    public Duke() throws IOException {
		UI.start();
		//ArrayList<Task> tasks = new ArrayList<Task>();
		storage = new Storage("data/duke.txt");

		Parser parse = new Parser(storage.getTasks(), ui, storage);
		parse.parserRead();
	}
}
