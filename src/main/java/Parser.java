import java.io.FileNotFoundException;

public class Parser {
    private Storage storage; private TaskList tasks;
    public Parser(Storage storage, TaskList tasks){
        this.storage = storage;
        this.tasks = tasks;
    }

    public void read(String command) throws FileNotFoundException {
        String[]words = command.split(" ");

        if (command.equals("EmptyOutput")) {                                                                            //IF EMPTYOUTPUT
            storage.emptyOutput();
            System.out.println("Output Emptied");
        }else if ( (words.length==2) && (words[0].equals("done")) && (isNumeric(words[1])) ) {                          //IF DONE
            try {
                int val = Integer.parseInt(words[1]);
                tasks.taskDone(val-1);
                System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(val-1));
                storage.printToOutput(tasks);
            } catch(Exception e) {
                System.out.println("Error, you have entered an invalid number");
            }
        }else if((words.length==2)&&(words[0].equals("delete"))&&(isNumeric(words[1]))){                                //IF DELETE
            try {
                int val = Integer.parseInt(words[1]);
                System.out.println("Noted. I've removed this task:"+ "\n" + tasks.taskPrint(val-1) +
                        "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.");
                tasks.remove(val - 1);
                storage.printToOutput(tasks);
            }catch(Exception e){
                System.out.println("Error, you have entered an invalid number");
            }
        }else if(command.equals("list")){                                                                               //IF LIST
            tasks.printForList();
        }else{

            try {
                String[]splitwords = command.trim().split("\\s",2);
                String midcommand = splitwords[1].trim();

                if (splitwords[0].equals("todo")) {                                                                     //IF TODO
                    if (midcommand.length() != 0) {
                        tasks.add(new ToDo(midcommand));
                        storage.printToOutput(tasks);
                    } else {
                        throw new Exception();
                    }
                } else if (splitwords[0].equals("deadline")) {                                                          //IF DEADLINE
                    if (midcommand.length() != 0) {
                        tasks.add(new Deadline(midcommand));
                        storage.printToOutput(tasks);
                    } else {
                        throw new Exception();
                    }
                } else if (splitwords[0].equals("event")) {                                                             //IF EVENT
                    if (midcommand.length() != 0) {
                        tasks.add(new Event(midcommand));
                        storage.printToOutput(tasks);
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new IllegalArgumentException();
                }

                System.out.println("Got it. I've added this task:" + "\n" + tasks.printLatest()
                        + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
            }catch(IllegalArgumentException e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
            }catch(DukeException e){
                System.out.println("☹ OOPS!!! The date format is wrong.");
            }catch(Exception e){
                System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
