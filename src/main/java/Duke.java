import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static Scanner sc = new Scanner(System.in);

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    public Duke() throws FileNotFoundException, IOException {

        this.ui = new UI();
        this.storage = new Storage("../duke.txt");

        ArrayList<Task> existing = storage.readFileContents();
        this.taskList = new TaskList(existing);
        this.storage.writeToFile("");


    }


    public static void main(String[] args) throws IOException  {

       new Duke().run();

    }

    public static String promptEntry() {
       return sc.next();
    }

    public void run() throws IOException {

        this.ui.welcome();

        String command = promptEntry();

        while (!command.equals("bye")) {

            try {
                switch (command) {

                    case "list":
                        this.taskList.printList();
                        break;

                    case "todo":
                        String task = sc.nextLine().trim();
                        if(!task.isEmpty()) {
                            ToDo newTodo = new ToDo(task);
                            this.taskList.addTask(newTodo);
                        } else {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;

                    case "deadline":
                        String wholeTask = sc.nextLine().trim();
                        int index = wholeTask.indexOf('/');
                        if(index > 0) {
                            //what the task is
                            String description = wholeTask.substring(0, index).trim();
                            //when it is due by
                            String date = wholeTask.substring(index + 4).trim();
                            if(description.isEmpty() || date.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                        " It must be in the format <description> /by <date/time> ");
                            } else {
                                Deadline newDeadlineTask = new Deadline(description, date);
                                this.taskList.addTask(newDeadlineTask);
                            }
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                    " It must be in the format <description> /by <date/time> ");
                        }
                        break;

                    case "event":
                        String eventAndDate = sc.nextLine();
                        int index2 = eventAndDate.indexOf('/');
                        if(index2 > 0) {
                            //what the task is
                            String eventDescr = eventAndDate.substring(0, index2).trim();
                            //when it is due by
                            String dateAndTime = eventAndDate.substring(index2 + 4).trim();
                            if(eventDescr.isEmpty() || dateAndTime.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                        " It must be in the format <description> /at <start and end of specific time> ");
                            } else {
                                Event newEventTask = new Event(eventDescr, dateAndTime);
                                this.taskList.addTask(newEventTask);
                            }
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                    " It must be in the format <description> /at <start and end of specific time> ");
                        }
                        break;

                    case "delete":
                        int taskToBeDeleted = sc.nextInt() -1;
                        this.taskList.deleteTask(taskToBeDeleted);
                        break;

                    case "done":
                        int taskNumber = sc.nextInt() - 1;
                        this.taskList.completeTask(taskNumber);
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");


                }


            } catch (DukeException e) {
                System.out.println(e);
            }

            command = promptEntry();


        }
        //after all commands are done, we will save the updated list into the txt file.
        ArrayList<Task> updated = this.taskList.getList();

        if(!updated.isEmpty()) {
            for (Task task : updated) {
                if(storage.isFileEmpty()) {
                        storage.writeToFile(task.toTextFile());
                } else {
                    storage.appendToFile(task.toTextFile());
                }
            }
        }

        this.ui.goodbye();
    }


}
