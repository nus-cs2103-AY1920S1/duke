import java.util.Scanner;

public class Duke {

    public static Scanner sc = new Scanner(System.in);

    private TaskList taskList;
    private UI ui;

    public Duke() {
        this.ui = new UI();
        this.taskList = new TaskList();

    }

    public static void main(String[] args) {

        new Duke().run();

    }


    public static void printCommand(String command) {
        System.out.println(command);
    }

    public static String promptEntry() {
       return sc.next();
    }

    public void run() {

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
                        String wholeTask = sc.nextLine();
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
                            String date2 = eventAndDate.substring(index2 + 4).trim();
                            if(eventDescr.isEmpty() || date2.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                        " It must be in the format <description> /at <start and end of specific time> ");
                            } else {
                                Event newEventTask = new Event(eventDescr, date2);
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
        this.ui.goodbye();
    }














}
