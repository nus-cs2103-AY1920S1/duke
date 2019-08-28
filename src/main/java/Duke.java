import java.io.FileNotFoundException;
import java.text.ParseException;

public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException | FileNotFoundException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMsg();
        boolean dukeIsOn = true;
        String input;
        while (dukeIsOn) {
            input = ui.getInput();
            try {
                dukeIsOn = tasks.parseInput(input, true);
            } catch (UnknownCmdDukeException e){
                ui.errorUCDE();
            } catch (EmptyTodoDscDukeException e) {
                ui.errorETDDE();
            } catch (EmptyDeadlineDscDukeException e) {
                ui.errorEDDDE();
            } catch (EmptyEventDscDukeException e) {
                ui.errorEEDDE();
            } catch (NoDateDukeException e) {
                ui.errorNDDE();
            } catch (InvalidTaskIndexDukeException e) {
                ui.errorITIDE();
            } catch (NumberFormatException e) {
                ui.errorNFE();
            } catch (DukeException e) {
                ui.errorDE(e.getMessage());
            } catch (ParseException e) {
                ui.errorPE();
            }
        }
        storage.saveDuke(tasks.saveInfo());
    }

    public static void main(String[] args) {
        new Duke("../data/savedList.txt").run();
    }
        //
//        Scanner sc = new Scanner(System.in);
//        String input;
//        boolean dukeIsOn = true;
//        String filePath = "../data/savedList.txt";
//        TaskList myTasks = new TaskList(filePath);
//        myTasks.loadDuke(filePath);
//        while(dukeIsOn){
//            input = sc.nextLine().trim();
//            try {
//                dukeIsOn = myTasks.parseInput(input, true);
//            } catch (UnknownCmdDukeException e){
//                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//            } catch (EmptyTodoDscDukeException e) {
//                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
//            } catch (EmptyDeadlineDscDukeException e) {
//                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
//            } catch (EmptyEventDscDukeException e) {
//                System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
//            } catch (NoDateDukeException e) {
//                System.out.println("☹ OOPS!!! You need to provide a date, with / to indicate it:-(");
//            } catch (InvalidTaskIndexDukeException e) {
//                System.out.println("☹ OOPS!!! You need to provide a valid task number :-(");
//            } catch (NumberFormatException e) {
//                System.out.println("☹ OOPS!!! You need to provide a valid number :-(");
//            } catch (DukeException e) {
//                System.out.println("☹ OOPS!!! Something went wrong" + e.getMessage());
//            } catch (ParseException e) {
//                System.out.println("Something went wrong! " + e.getMessage());
//            }
//        }
//    }

}
