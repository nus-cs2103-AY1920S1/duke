import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.getTasks());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }
    String getResponse(String input) {

        Parser parser = new Parser(input, ui, tasks);
        if (parser.getCommand().equals("bye")) {
            return ui.sayGoodbye();
        }
        try {
            return parser.doCommand();
        } catch (Exception e) {
            return "Something went wrong :( Please try again";
        }

//        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
//        try {
//            storage.updateTasks(filePath, tasks.getList());
//            return "Updating list....";
//        } catch (Exception e) {
//            return "Tasks not saved";
//        }
    }

    public void updateDb() {
        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
        try {
            storage.updateTasks(filePath, tasks.getList());
        } catch (Exception e) {
        }
    }


    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Parser parser = new Parser(sc.nextLine(), ui, tasks);
            if (parser.getCommand().equals("bye")) {
                ui.sayGoodbye();
                break;
            }
            try {
                parser.doCommand();
            } catch (Exception e) {
                System.out.println("Something went wrong :( Please try again");
            }
        }
        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
        try {
            storage.updateTasks(filePath, tasks.getList());
        } catch (Exception e) {
            System.out.println("Tasks not saved");
        }
    }

    public static void main(String[] args)  {
        String filePath = "C:\\Users\\johnn\\CS2103\\duke\\tasks.txt";
        new Duke(filePath).run();
    }


    /**
     * Converts a Date object to a String object in dd/MM/yyyy HHmm format
     *
     * @param date Date object
     * @return String
     */
    public static String dateToStringConverter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String sDate = sdf.format(date);
        return sDate;
    }


}
