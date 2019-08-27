import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
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
        String filePath = "C:\\Users\\johnn\\CS2103\\Week2\\tasks.txt";
        try {
            storage.updateTasks(filePath, tasks.getList());
        } catch (Exception e) {
            System.out.println("Tasks not saved");
        }
    }

    public static void main(String[] args)  {
        String filePath = "C:\\Users\\johnn\\CS2103\\Week2\\tasks.txt";
        new Duke(filePath).run();
    }


    public static String dateToStringConverter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HHmm");
        String sDate = sdf.format(date);
        return sDate;
    }


}
