import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    /**
     * Prints introduction, takes in user input, and processes it
     * @param tasks gives the TaskList that contains all tasks
     * @param storage gives the storage object to retrieve & output data
     * @throws FileNotFoundException
     */
    public void run(TaskList tasks, Storage storage) throws FileNotFoundException {
        final String DUKE_LOGO = " ____        _        \n"
                               + "|  _ \\ _   _| | _____ \n"
                               + "| | | | | | | |/ / _ \\\n"
                               + "| |_| | |_| |   <  __/\n"
                               + "|____/ \\__,_|_|\\_\\___|\n";
        final String LINE_BORDER = "____________________________________________________________";

        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(LINE_BORDER + "\n" + "Hello! I'm Duke" + "\n" +
                            "What can I do for you?" + "\n" + LINE_BORDER);

        Parser parser = new Parser(storage, tasks);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            String[]words = command.split(" ");
            System.out.println(LINE_BORDER);

            if (command.equals("bye")) {                                                                                //IF BYE
                System.out.println("Bye. Hope to see you again soon!" + "\n" + LINE_BORDER);
                break;
            } else if (command.equals("EmptyOutput")) {
                parser.EmptyOutput();
            } else if ( (words.length==2) && (words[0].equals("done")) && (isNumeric(words[1])) ) {                     //IF DONE
                parser.done(command);
            } else if ((words.length==2)&&(words[0].equals("delete"))&&(isNumeric(words[1]))){                          //IF DELETE
                parser.delete(command);
            } else if (command.equals("list")){                                                                         //IF LIST
                tasks.printForList();
            } else if(words[0].equals("find")){
                parser.find(command);
            } else {
                try {
                    String[]splitWords = command.trim().split("\\s",2);

                    if (splitWords[0].equals("todo")) {                                                                 //IF TODO
                        parser.createTodo(command);
                    } else if (splitWords[0].equals("deadline")) {                                                      //IF DEADLINE
                        parser.createDeadline(command);
                    } else if (splitWords[0].equals("event")) {                                                         //IF EVENT
                        parser.createEvent(command);
                    } else {
                        throw new IllegalArgumentException();
                    }

                    System.out.println("Got it. I've added this task:" + "\n" + tasks.printLatest()
                            + "\n" + "Now you have " + tasks.size() + " tasks in the list.");

                } catch (IllegalArgumentException e){
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                } catch (DukeException e){
                    System.out.println("☹ OOPS!!! The date format is wrong.");
                } catch (Exception e){
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
            }
            System.out.println(LINE_BORDER);
        }
    }

    /**
     * prints error message if file is not available
     */
    public void showLoadingError(){
        System.out.println("File not available");
    }

    /**
     * determines whether parameter is an integer
     * @param str takes in the string that will be checked
     * @return boolean value
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
