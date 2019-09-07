import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Duke extends Application {

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
		storage = new Storage("src/main/java/data/duke.txt");

		Parser parse = new Parser(storage.getTasks(), ui, storage);
		parse.parserRead();
	}


	@Override
	public void start(Stage stage) {
    	Label helloWorld = new Label("i <3 u");
    	helloWorld.setFont(new Font("Arial", 50));
    	Scene scene = new Scene(helloWorld);

    	stage.setScene(scene);
    	stage.show();
	}
}
