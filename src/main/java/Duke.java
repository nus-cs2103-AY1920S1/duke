import java.util.Scanner;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I am Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String input = "";
        while (!input.equals("bye")) {
            input = sc.next();
            if(!input.equals("bye")) {
                System.out.println(
                        "    ____________________________________________________________\n" +
                                "     " + input + "\n" +
                                "    ____________________________________________________________\n");
            }else {
                System.out.println(
                        "    ____________________________________________________________\n" +
                                "     Bye. Hope to see you again soon!\n" +
                                "    ____________________________________________________________\n");
            }
        }

    }
//    @Override
//    public void start(Stage stage) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        Label label = new Label(logo); // Creating a new Label control
//        Scene scene = new Scene(label); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
}
