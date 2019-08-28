import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {


    public void run(TaskList tasks, Storage storage) throws FileNotFoundException {
        Parser parser = new Parser(storage, tasks);
                Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";

        while(true){
            String command = sc.nextLine();

            System.out.println(line);

            if (command.equals("bye")) {                                                                                //IF BYE
                System.out.println("Bye. Hope to see you again soon!" + "\n" + line);
                break;
            }else{
                parser.read(command);
            }

            System.out.println(line);
        }
    }


    public void showLoadingError(){
        System.out.println("File not available");
    }

}
