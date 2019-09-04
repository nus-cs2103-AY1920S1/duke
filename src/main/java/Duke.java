import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;


public class Duke {

	private static UI ui;
	private Storage storage;
	private Tasklist tasklist;

    public static void main(String[] args) throws IOException {
		new Duke();
    }

    public Duke() throws IOException {
		UI.start();
		//ArrayList<Task> tasks = new ArrayList<Task>();
		storage = new Storage("data/duke.txt");

		Parser parse = new Parser(storage.getTasks(), ui, storage);
		parse.parserRead();
	}
}
